package com.roxi.oauth.service;

import com.roxi.oauth.bean.User;
import com.roxi.oauth.excpetion.MyException;
import com.roxi.oauth.mapper.BasicMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Roxi酱
 */
@Service
public class BasicService {
    @Resource
    BasicMapper basicMapper;
    public void register(User user){
        basicMapper.insert(user);
    }

    public User loginUser(User user){
        if("".equals(user.getName()) || "".equals(user.getPassword())){
            throw new MyException(002,"名字和密码不能为空");
        }
        user=basicMapper.select(user);
        return user;
    }
}
