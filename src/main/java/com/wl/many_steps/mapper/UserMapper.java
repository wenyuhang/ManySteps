package com.wl.many_steps.mapper;

import com.wl.many_steps.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/8/17 15:43
 * desc   :
 */
public interface UserMapper {
    @Insert(" insert into user ( name,headimgurl,openid,unionid,phone,session_key,access_token,steps_total,coin_total,energy_total,createdate) " +
            "values (#{user.name},#{user.headimgurl},#{user.openid},#{user.unionid},#{user.phone},#{user.session_key},#{user.access_token}," +
            "#{user.steps_total},#{user.coin_total},#{user.energy_total},#{user.createdate}) ")
    @Options(useGeneratedKeys=true, keyProperty="user.id", keyColumn="id")
    int add(@Param("user")User user);

    @Delete(" delete from user where id= #{id} ")
    void delete(int id);

    @Select("select * from user where id= #{id} ")
    User get(int id);

    @Select("select * from user where openid= #{openid} ")
    User getByOpenid(String openid);

    @Update("update user set name=#{user.name},headimgurl=#{user.headimgurl},openid=#{user.openid},phone=#{user.phone},session_key=#{user.session_key}," +
            "access_token=#{user.access_token},steps_total=#{user.steps_total},coin_total=#{user.coin_total},energy_total=#{user.energy_total}," +
            "createdate=#{user.createdate} where id=#{user.id}")
    int update(@Param("user") User user);

    @Select(" select * from user ")
    List<User> list();
}
