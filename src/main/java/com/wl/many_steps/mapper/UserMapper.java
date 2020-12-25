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
    @Insert(" insert into user ( name,headimgurl,openid,unionid,phone,session_key,access_token,steps_total,coin_total,invite_total,createdate) " +
            "values (#{user.name},#{user.headimgurl},#{user.openid},#{user.unionid},#{user.phone},#{user.session_key},#{user.access_token}," +
            "#{user.steps_total},#{user.coin_total},#{user.invite_total},#{user.createdate}) ")
    @Options(useGeneratedKeys=true, keyProperty="user.id", keyColumn="id")
    int add(@Param("user")User user);

    @Delete(" delete from user where id= #{id} ")
    void delete(int id);

    @Select("select * from user where id= #{id} ")
    User get(int id);

    @Select("select * from user where openid= #{openid} ")
    User getByOpenid(String openid);

    @Update("update user set name=#{user.name},headimgurl=#{user.headimgurl},openid=#{user.openid},phone=#{user.phone},session_key=#{user.session_key}," +
            "access_token=#{user.access_token},account_status=#{user.account_status},steps_total=#{user.steps_total},coin_total=#{user.coin_total},invite_total=#{user.invite_total}," +
            "createdate=#{user.createdate} where id=#{user.id}")
    int update(@Param("user") User user);

    @Select("SELECT COUNT(*) FROM user")
    int getUserCount();

    @Select("select * from user ORDER BY createdate DESC")
    List<User> list();

    @Select("SELECT * FROM user where id!=390 ORDER BY steps_total DESC")
    List<User> stepsRankList();

    @Select("SELECT rowno FROM (SELECT id,steps_total,(@rowno:=@rowno+1) AS rowno FROM user,(SELECT (@rowno:=0)) b where id!=390 ORDER BY steps_total DESC) c WHERE id = #{id}")
    int getUserStepsRanking(int id);

    @Select("SELECT * FROM user ORDER BY invite_total DESC")
    List<User> inviteRankList();

    @Select("SELECT rowno FROM (SELECT id,invite_total,(@rowno:=@rowno+1) AS rowno FROM user,(SELECT (@rowno:=0)) b ORDER BY invite_total DESC) c WHERE id = #{id}")
    int getUserInviteRank(int id);
}
