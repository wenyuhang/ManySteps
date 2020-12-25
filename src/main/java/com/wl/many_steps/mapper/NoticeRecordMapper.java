package com.wl.many_steps.mapper;

import com.wl.many_steps.pojo.NoticesRecord;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/12/14 16:57
 * desc   :
 */
public interface NoticeRecordMapper {
    @Insert("insert into m_notice_record (uid,p_status,p_desc,steps,coin,create_time,update_time) " +
            "values (#{noticesRecord.uid},#{noticesRecord.p_status},#{noticesRecord.p_desc},#{noticesRecord.steps}," +
            "#{noticesRecord.coin},#{noticesRecord.create_time},#{noticesRecord.update_time}) ")
    @Options(useGeneratedKeys=true, keyProperty="address.id", keyColumn="id")
    int add(@Param("noticesRecord") NoticesRecord noticesRecord);

    @Delete(" delete from m_notice_record where uid= #{id} ")
    int delete(int id);

    @Update("update m_notice_record set uid=#{noticesRecord.uid},p_status=#{noticesRecord.p_status}," +
            "p_desc=#{noticesRecord.p_desc},steps=#{noticesRecord.steps},coin=#{noticesRecord.coin}," +
            "create_time=#{noticesRecord.create_time},update_time=#{noticesRecord.update_time} where id=#{noticesRecord.id} ")
    int update(@Param("noticesRecord") NoticesRecord noticesRecord);

    @Select("SELECT * FROM m_notice_record WHERE uid=#{uid}")
    List<NoticesRecord> getUserNotices(@Param("uid")int uid);

    @Select("SELECT * FROM m_notice_record WHERE p_status=b'0' AND uid=#{uid}")
    int getUserNoticesCount(@Param("uid")int uid);

    @Select("select * from m_notice_record")
    @Results({
            @Result(id=true,column="id",property="id"),
            @Result(column="uid",property="uid"),
            @Result(column="p_status",property="p_status"),
            @Result(column="p_desc",property="p_desc"),
            @Result(column="steps",property="steps"),
            @Result(column="coin",property="coin"),
            @Result(column="create_time",property="create_time"),
            @Result(column="update_time",property="update_time"),
            @Result(column="uid",property="user",one=@One(select="com.wl.many_steps.mapper.UserMapper.get",fetchType= FetchType.EAGER))
    })
    List<NoticesRecord> list();

}
