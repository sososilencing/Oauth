package com.roxi.oauth.mapper;

import com.roxi.oauth.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

/**
 * @author Roxi酱
 */
@Mapper
public interface BasicMapper {
    /**
     * 添加用户
     * @param user
     * @return null
     */
    @Insert("insert into user(id,power,name,password) values(#{id},#{power},#{name},#{password})")
    @Options(useGeneratedKeys = true,keyColumn = "id",keyProperty = "id")
    void insert(User user);

    /**
     * 用户登陆查询
     * @param user
     * @return
     */
    @Select("select * from user where name=#{name} and password=#{password}")
    User select(User user);
}
