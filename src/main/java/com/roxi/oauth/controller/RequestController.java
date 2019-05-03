package com.roxi.oauth.controller;


import com.roxi.oauth.bean.User;
import com.roxi.oauth.service.BasicService;
import com.roxi.oauth.service.RequsetService;
import com.roxi.oauth.utils.NetWorkUtil;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.HttpCookie;
import java.net.URLEncoder;

/**
 * @author Roxi酱
 */

@RestController
public class RequestController {
    // 这里模拟的是其他的app 并不能直接访问数据库
    @Autowired
    BasicService basicService;

    @Value("${oauth.info.url}")
    String urlHead;
    @GetMapping("/GetCode")
    public void getCode(HttpServletRequest request,HttpServletResponse response) throws IOException, JSONException {
      //  String name=  request.getParameter("name");
      //  String password=  request.getParameter("password");
        String backurl = "http://localhost:8080/callback";
        String url=urlHead+"/oauth2/authorize?redirect_uri=" + URLEncoder.encode(backurl,"utf-8");
               // "&name="+name+"&password="+password;
        response.sendRedirect(url);
    }

    @GetMapping("/callback")
    public String getMessage(String code) throws IOException {
        String url=urlHead+"/verify?token="+code;
        String json=NetWorkUtil.getMessage(url);
        return json;
    }
}
