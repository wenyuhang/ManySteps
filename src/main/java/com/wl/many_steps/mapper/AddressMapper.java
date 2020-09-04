package com.wl.many_steps.mapper;

import com.wl.many_steps.pojo.Address;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/9/4 11:59
 * desc   :
 */
public interface AddressMapper {
    @Insert("insert into address ( uid,receiver,mobile,post,createdate) " +
            "values (#{address.uid},#{address.receiver},#{address.mobile},#{address.post},#{address.createdate}) ")
    @Options(useGeneratedKeys=true, keyProperty="address.id", keyColumn="id")
    int add(@Param("address") Address address);

    @Delete(" delete from address where uid= #{uid} ")
    int delete(int uid);

    @Update("update address set uid=#{address.uid},receiver=#{address.receiver},mobile=#{address.mobile}," +
            "post=#{address.post},createdate=#{address.createdate}where id=#{address.id} ")
    int update(@Param("address") Address address);

    @Select("select * from address where uid= #{uid} ")
    Address get(int uid);

    @Select(" select * from address ")
    List<Address> list();
}
