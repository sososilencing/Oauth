package com.roxi.oauth.controller;

import com.roxi.oauth.bean.User;
import com.roxi.oauth.excpetion.MyException;
import com.roxi.oauth.service.BasicService;
import com.roxi.oauth.service.OauthTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLDecoder;

/**
 * @author Roxi酱
 */
@Controller
public class BasicController {
    @Autowired
    BasicService basicService;
    @Autowired
    OauthTokenService oauthTokenService;
    @RequestMapping(value = "/register",method = RequestMethod.GET)
    @ResponseBody
    public String register(@RequestParam("name") String name,@RequestParam("password") String password){
        User user=new User(name,password);
        user.setPower("level.1");
        basicService.register(user);
        return "注册成功";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(HttpServletRequest request,  String name,  String password){
        HttpSession session=request.getSession();
        User user=new User(name,password);
        user=basicService.loginUser(user);
        if(user==null){
            return null;
        }
        session.setAttribute("user",user.getId());
        return "login";
    }

    @RequestMapping(value = "/oauth2/authorize",method = RequestMethod.GET)
    // 他传给我以一个code  我要回传他一个code 在域名之中
    public String oauth() throws IOException {
      return "login";
    }

    @RequestMapping(value = "shunbian",method = RequestMethod.POST)
    public void shunbian(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String redirect_uri=request.getParameter("redirect_uri");
        String name=request.getParameter("name");
        String password=request.getParameter("password");
          User user=new User(name,password);
        user =basicService.loginUser(user);
        if(user==null){
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("账号或密码错误");
        }
        else {
            String token = null;
            try {
                token = oauthTokenService.creatToken(user, "getUserMessage");
            } catch (MyException e) {
                return;
            }
            redirect_uri = URLDecoder.decode(redirect_uri, "utf-8");
            String url = redirect_uri + "?code=" + token;
            response.sendRedirect(url);
        }
    }
    @RequestMapping(value = "/verify",method = RequestMethod.GET)
    @ResponseBody
    public User verify(String token){
        User user=oauthTokenService.verifyToken(token);
        return user;
    }
}
