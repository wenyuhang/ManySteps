package com.wl.many_steps.mapper;

import com.wl.many_steps.pojo.Order;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/8/17 15:43
 * desc   :
 */
public interface OrderMapper {
    @Insert(" insert into order_ ( uid,pid,adid,status,ordercode,couriernumber,createdate) values " +
            "(#{order.uid},#{order.pid},#{order.adid},#{order.status},#{order.ordercode},#{order.couriernumber},#{order.createdate}) ")
    @Options(useGeneratedKeys=true, keyProperty="order.id", keyColumn="id")
    int add(@Param("order") Order order);

    @Delete(" delete from order_ where id= #{id} ")
    int delete(int id);

    @Delete(" delete from order_ where ordercode= #{ordercode} ")
    int deleteByOrderCode(String code);

    @Select("select * from order_ where id= #{id} ")
    Order get(int id);

    @Update("update order_ set name=#{name} where id=#{id} ")
    int update(Order order);

    @Select(" select * from order_ ORDER BY createdate DESC")
    @Results({
            @Result(id=true,column="id",property="id"),
            @Result(column="uid",property="uid"),
            @Result(column="pid",property="pid"),
            @Result(column="adid",property="adid"),
            @Result(column="status",property="status"),
            @Result(column="ordercode",property="ordercode"),
            @Result(column="couriernumber",property="couriernumber"),
            @Result(column="createdate",property="createdate"),
            @Result(column="pid",property="product",one=@One(select="com.wl.many_steps.mapper.ProductMapper.get",fetchType= FetchType.EAGER)),
            @Result(column="uid",property="user",one=@One(select="com.wl.many_steps.mapper.UserMapper.get",fetchType= FetchType.EAGER))
    })
    List<Order> list();

    @Select(" select * from order_ where uid= #{uid} ORDER BY createdate DESC")
    @Results({
            @Result(id=true,column="id",property="id"),
            @Result(column="uid",property="uid"),
            @Result(column="pid",property="pid"),
            @Result(column="adid",property="adid"),
            @Result(column="status",property="status"),
            @Result(column="ordercode",property="ordercode"),
            @Result(column="couriernumber",property="couriernumber"),
            @Result(column="createdate",property="createdate"),
            @Result(column="pid",property="product",one=@One(select="com.wl.many_steps.mapper.ProductMapper.get",fetchType= FetchType.EAGER)),
            @Result(column="uid",property="user",one=@One(select="com.wl.many_steps.mapper.UserMapper.get",fetchType= FetchType.EAGER))
    })
    List<Order> userList(int uid);


    @Select(" select * from order_ where pid= #{pid} ORDER BY createdate DESC")
    @Results({
            @Result(id=true,column="id",property="id"),
            @Result(column="uid",property="uid"),
            @Result(column="pid",property="pid"),
            @Result(column="adid",property="adid"),
            @Result(column="status",property="status"),
            @Result(column="ordercode",property="ordercode"),
            @Result(column="couriernumber",property="couriernumber"),
            @Result(column="createdate",property="createdate"),
            @Result(column="pid",property="product",one=@One(select="com.wl.many_steps.mapper.ProductMapper.get",fetchType= FetchType.EAGER)),
            @Result(column="uid",property="user",one=@One(select="com.wl.many_steps.mapper.UserMapper.get",fetchType= FetchType.EAGER))
    })
    List<Order> productList(int pid);
}
