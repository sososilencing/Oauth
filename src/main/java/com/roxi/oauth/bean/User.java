package com.roxi.oauth.bean;

import lombok.Data;

/**
 * @author Roxi酱
 */
@Data
public class User {
    int id;
    String power;
    String name;
    String password;
    public User(String name,String password){
        this.name=name;
        this.password=password;
    }
    public User(){

    }
    @Override
    public String toString(){
        return "{"+"\'id\':"+id+"\'name\':"+name+"\'password\':"+password+"}";
    }
}
