package com.wl.many_steps.mapper;

import com.wl.many_steps.pojo.Order;
import org.apache.ibatis.annotations.*;

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

    @Select(" select * from order_ ")
    List<Order> list();

    @Select(" select * from order_ where uid= #{uid}")
    List<Order> listByUser(int uid);

    @Select(" select * from order_ where pid= #{pid}")
    List<Order> listByProduct(int pid);
}
