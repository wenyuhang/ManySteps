package com.wl.many_steps.mapper;


import com.wl.many_steps.pojo.StepsRecord;
import org.apache.ibatis.annotations.*;

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

    @Select(" select * from stepsrecord")
    List<StepsRecord> list();

    @Select(" select * from stepsrecord where uid = #{uid}")
    List<StepsRecord> listByUser(int uid);
}
