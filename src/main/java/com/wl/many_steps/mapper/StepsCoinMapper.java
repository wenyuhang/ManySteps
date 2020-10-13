package com.wl.many_steps.mapper;


import com.wl.many_steps.pojo.StepsCoin;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/8/17 16:50
 * desc   :
 */
public interface StepsCoinMapper {
    @Insert("insert into stepscoin(uid,coin,rundate,convertsteps,createdate) " +
            "values (#{sr.uid},#{sr.coin},#{sr.rundate},#{sr.convertsteps},#{sr.createdate}) ")
    @Options(useGeneratedKeys=true, keyProperty="sr.id", keyColumn="id")
    int add(@Param("sr") StepsCoin sr);

    @Delete(" delete from stepscoin where id= #{id} ")
    void delete(int id);

    @Update("update stepscoin set uid=#{sr.uid},coin=#{sr.coin},price=#{sr.rundate},stock=#{sr.convertsteps},subTitle=#{sr.createdate} where id=#{sr.id} ")
    int update(@Param("sr") StepsCoin sr);

    @Select("select * from stepscoin where id= #{id} ")
    StepsCoin get(int id);

    @Select(" select * from stepscoin")
    List<StepsCoin> list();

    @Select(" select * from stepscoin where uid = #{uid}")
    List<StepsCoin> listByUser(int uid);
}