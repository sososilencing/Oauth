package com.roxi.oauth.utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author Roxi酱
 */
public class NetWorkUtil {
    public static String getMessage(String url1) throws IOException{
        URL url=new URL(url1);
        URLConnection urlConnection=url.openConnection();
        InputStream is=urlConnection.getInputStream();
        InputStreamReader isr=new InputStreamReader(is,"utf-8");
        char []chars=new char[1024*8];
        int len;
        StringBuffer sb=new StringBuffer();
        while ((len=isr.read(chars,0,chars.length))!=-1){
            sb.append(new String(chars,0,len));
        }
        return sb.toString();
    }
}
