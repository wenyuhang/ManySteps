package com.wl.many_steps.mapper;

import com.wl.many_steps.pojo.InviteRela;
import com.wl.many_steps.pojo.StepsCoin;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/10/14 15:58
 * desc   :
 */
public interface InviteRelaMapper {
    @Insert(" insert into invite_rela (uid,inviter_id,createdate) " +
            "values (#{inviterela.uid},#{inviterela.inviter_id},#{inviterela.createdate}) ")
    @Options(useGeneratedKeys=true, keyProperty="inviterela.id", keyColumn="id")
    int add(@Param("inviterela")InviteRela inviteRela);

    @Delete(" delete from invite_rela where id= #{id} ")
    int delete(int id);

    @Insert(" update into invite_rela set " +
            "uid=#{inviterela.uid},inviter_id=#{inviterela.inviter_id},createdate=#{inviterela.createdate}")
    int update(@Param("inviterela")InviteRela inviteRela);

    @Select("select * from invite_rela where uid= #{id} ")
    InviteRela get(int id);


    @Select("SELECT COUNT(inviter_id) FROM invite_rela WHERE inviter_id= #{inviter_id}")
    int getInviteNum(int inviter_id);

    @Select("select * from invite_rela where uid= #{uid}")
    List<InviteRela> getByUid(int uid);

    @Select("select * from invite_rela where inviter_id= #{inid} ")
    @Results({
            @Result(id=true,column="id",property="id"),
            @Result(column="uid",property="uid"),
            @Result(column="inviter_id",property="inviter_id"),
            @Result(column="createdate",property="createdate"),
            @Result(column="uid",property="user",one=@One(select="com.wl.many_steps.mapper.UserMapper.get",fetchType= FetchType.EAGER))
    })
    List<InviteRela> getByInviteid(int inid);
}
