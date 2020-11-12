package com.wl.many_steps.service;

import com.wl.many_steps.mapper.OrderMapper;
import com.wl.many_steps.pojo.Order;
import com.wl.many_steps.pojo.Product;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/10/10 11:47
 * desc   :
 */
@Service
public class OrderService {
    @Autowired
    OrderMapper orderMapper;

    /**
     * 添加订单
     * @param order
     * @return
     */
    public int add(Order order){
        return orderMapper.add(order);
    }

    /**
     * 根据订单号删除订单
     * @param ordercode 订单号
     * @return
     */
    public int deleteByOrderCode(String ordercode){
        return orderMapper.deleteByOrderCode(ordercode);
    }

    /**
     * 修改订单状态
     * @param order
     * @return
     */
    public int updata(Order order){
        return orderMapper.update(order);
    }
    /**
     * 根据订单号获取订单
     * @param ordercode
     * @return
     */
    public Order get(String ordercode){
        Order order = null;
        try {
            order = orderMapper.get(ordercode);
            if (null==order|| TextUtils.isEmpty(order.getOrdercode())){
                order = null;
            }
        }catch (Exception e){
            order = null;
        }
        return order;
    }

    /**
     * 根据用户id获取历史订单
     * @param uid
     * @return
     */
    public List<Order> listByUid(int uid){
        return orderMapper.userList(uid);
    }


    /**
     * 根据商品id获取历史订单
     * @param uid
     * @return
     */
    public List<Order> listByPid(int uid){
        return orderMapper.productList(uid);
    }


    /**
     * 获取全部订单
     * @return
     */
    public List<Order> list(){
        return orderMapper.list();
    }

}
