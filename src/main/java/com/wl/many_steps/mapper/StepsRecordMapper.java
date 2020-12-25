package com.wl.many_steps.mapper;


import com.wl.many_steps.pojo.StepsRecord;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/8/17 16:50
 * desc   :
 */
public interface StepsRecordMapper {
    @Insert("insert into stepsrecord (uid,steps,rundate,convertedsteps,createdate) " +
            "values (#{sr.uid},#{sr.steps},#{sr.rundate},#{sr.convertedsteps},#{sr.createdate}) ")
    @Options(useGeneratedKeys=true, keyProperty="sr.id", keyColumn="id")
    int add(@Param("sr") StepsRecord sr);

    @Delete(" delete from stepsrecord where id= #{id} ")
    void delete(int id);

    @Update("update stepsrecord set uid=#{sr.uid},steps=#{sr.steps},rundate=#{sr.rundate},convertedsteps=#{sr.convertedsteps},createdate=#{sr.createdate} where id=#{sr.id} ")
    int update(@Param("sr") StepsRecord sr);

    @Select("select * from stepsrecord where id= #{id} ")
    StepsRecord get(int id);

    @Select("SELECT COALESCE(SUM(steps),0) AS stepsrecord FROM stepsrecord WHERE uid = #{uid}")
    int sumSteps(@Param("uid")int uid);

    @Select("select * from stepsrecord where uid= #{uid} and rundate= #{rundate} ")
    StepsRecord getByUidAndRundate(@Param("uid")int uid,@Param("rundate")String rundate);

    @Select(" select * from stepsrecord ORDER BY createdate DESC")
    List<StepsRecord> list();

    @Select(" select * from stepsrecord where uid = #{uid} ORDER BY createdate DESC")
    List<StepsRecord> listByUser(int uid);

    @Select("SELECT id,uid,steps,rundate,convertedsteps,createdate,COUNT(*) AS COUNT FROM stepsrecord WHERE steps>=0 GROUP BY steps,uid HAVING COUNT>1")
    @Results({
            @Result(id=true,column="id",property="id"),
            @Result(column="uid",property="uid"),
            @Result(column="steps",property="steps"),
            @Result(column="rundate",property="rundate"),
            @Result(column="convertedsteps",property="convertedsteps"),
            @Result(column="createdate",property="createdate"),
            @Result(column="uid",property="user",one=@One(select="com.wl.many_steps.mapper.UserMapper.get",fetchType= FetchType.EAGER))
    })
    List<StepsRecord> getMonitorsData();

    @Select("SELECT rowno FROM (SELECT uid,steps,(@rowno:=@rowno+1) AS rowno FROM stepsrecord,(SELECT (@rowno:=0)) b WHERE TO_DAYS(createdate) = TO_DAYS(NOW()) AND id!=390 ORDER BY steps DESC) c WHERE uid = #{uid}")
    int getUserTodayRank(@Param("uid")int uid);

    @Select("SELECT steps FROM stepsrecord WHERE TO_DAYS(createdate) = TO_DAYS(NOW()) AND uid = #{uid}")
    int getUserTodaySteps(@Param("uid")int uid);
}
