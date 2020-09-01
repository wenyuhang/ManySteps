package com.wl.many_steps.service;

import com.wl.many_steps.mapper.ProductMapper;
import com.wl.many_steps.pojo.Product;
import com.wl.many_steps.pojo.User;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/9/1 15:38
 * desc   :
 */
@Service
public class ProductService {
    @Autowired
    ProductMapper productMapper;

    /**
     * 添加商品
     * @param product
     * @return
     */
    public int add(Product product){
        return productMapper.add(product);
    }

    /**
     * 根据名称获取商品
     * @param name
     * @return
     */
    public Product get(String name){
        Product product = null;
        try {
            product = productMapper.getByName(name);
            if (null==product|| TextUtils.isEmpty(product.getName())){
                product = null;
            }
        }catch (Exception e){
            product = null;
        }
        return product;
    }

    /**
     * 获取商品列表
     * @return
     */
    public List<Product> list(){
        return productMapper.list();
    }
}
