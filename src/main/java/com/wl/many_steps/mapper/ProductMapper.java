package com.wl.many_steps.mapper;

import com.wl.many_steps.pojo.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/8/17 15:43
 * desc   :
 */
public interface ProductMapper {
    @Insert("insert into product ( name,coin,price,stock,subTitle,imageurl,energy,createdate ) " +
            "values (#{product.name},#{product.coin},#{product.price},#{product.stock},#{product.subTitle}," +
            "#{product.imageurl},#{product.energy},#{product.createdate}) ")
    @Options(useGeneratedKeys=true, keyProperty="product.id", keyColumn="id")
    int add(@Param("product") Product product);

    @Delete(" delete from product where id= #{id} ")
    void delete(int id);

    @Update("update product set name=#{product.name},coin=#{product.coin},price=#{product.price},stock=#{product.stock},subTitle=#{product.subTitle}," +
            "imageurl=#{product.imageurl},energy=#{product.energy},createdate=#{product.createdate} where id=#{product.id} ")
    int update(@Param("product") Product product);

    @Select("select * from product where id= #{id} ")
    Product get(int id);



    @Select("select * from product where name= #{name} ")
    Product getByName(String name);

    @Select(" select * from product ")
    List<Product> list();
}
