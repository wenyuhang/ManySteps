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
    @Insert(" insert into user ( name,headimgurl,openid,unionid,phone,createdate,steps_total,energy_total ) values (#{user.name},#{user.headimgurl},#{user.openid},#{user.unionid},#{user.phone},#{user.createdate},#{user.steps_total},#{user.energy_total}) ")
    @Options(useGeneratedKeys=true, keyProperty="user.id", keyColumn="id")
    int add(@Param("user")User user);

    @Delete(" delete from user where id= #{id} ")
    void delete(int id);

    @Select("select * from user where id= #{id} ")
    User get(int id);

    @Select("select * from user where openid= #{openid} ")
    User getByOpenid(String openid);

    @Update("update user set name=#{name} where id=#{id} ")
    int update(User category);

    @Select(" select * from user ")
    List<User> list();
}
