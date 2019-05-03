package com.roxi.oauth.service;

import com.roxi.oauth.utils.NetWorkUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author Roxi酱
 */
@Service
public class RequsetService {
    public String getoauth(String urls) throws IOException {
        String token= NetWorkUtil.getMessage(urls);
        return token;
    }
}
