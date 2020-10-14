package com.wl.many_steps.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wl.many_steps.model.ApiResponse;
import com.wl.many_steps.pojo.*;
import com.wl.many_steps.service.OrderService;
import com.wl.many_steps.service.ProductService;
import com.wl.many_steps.service.UserService;
import com.wl.many_steps.utils.DateUtils;
import com.wl.many_steps.utils.RandomNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/10/10 11:49
 * desc   :
 */

@Validated
@RestController
@RequestMapping(value = "order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;


    /**
     * 下单前 查询余额
     * @param balanceBean
     * @return
     */
    @PostMapping(value = "/checkBalance")
    public ApiResponse check(@Validated @RequestBody BalanceBean balanceBean){

        User user = userService.get(balanceBean.getUid());
        if (null==user){
            return ApiResponse.of(999,"用户不存在",null);
        }
        Product product = productService.get(balanceBean.getPid());
        if (null==product) {
            return ApiResponse.of(999, "商品不存在", null);
        }
        float coin = product.getCoin();
        float energy = product.getEnergy();
        float coin_total = user.getCoin_total();
        float energy_total = user.getEnergy_total();

        if (coin_total<coin){
            return ApiResponse.of(999,"金币余额不足",null);
        }
//        if (energy_total<energy){
//            return ApiResponse.of(999,"包邮能量不足",null);
//        }
        return ApiResponse.ofSuccess(null);
    }

    /**
     * 用户进行下单 扣款
     * @param placeOrderBean
     * @return
     */
    @PostMapping(value = "/placeOrder")
    public ApiResponse placeOrder(@Validated @RequestBody PlaceOrderBean placeOrderBean){
        //检查余额
        User user = userService.get(placeOrderBean.getUid());
        if (null==user){
            return ApiResponse.of(999,"用户不存在",null);
        }
        Product product = productService.get(placeOrderBean.getPid());
        if (null==product) {
            return ApiResponse.of(999, "商品不存在", null);
        }
        float coin = product.getCoin();
        float energy = product.getEnergy();
        int stock = product.getStock();
        float coin_total = user.getCoin_total();
        float energy_total = user.getEnergy_total();

        if (stock<=0){
            return ApiResponse.of(999,"商品已被兑换完毕，工作人员正在加紧补货！~",null);
        }
        if (coin_total<coin){
            return ApiResponse.of(999,"金币余额不足",null);
        }
//        if (energy_total<energy){
//            return ApiResponse.of(999,"包邮能量不足",null);
//        }
        //创建订单
        Order order = new Order();
        order.setUid(placeOrderBean.getUid());
        order.setPid(placeOrderBean.getPid());
        order.setAdid(placeOrderBean.getAdid());
        order.setOrdercode(RandomNumber.getInstance().GetRandom());
        order.setStatus(10);
        order.setCreatedate(DateUtils.stampToDate(System.currentTimeMillis()));
        int code = orderService.add(order);
        if (code==0){
            return ApiResponse.of(999,"操作失败请重试",null);
        }
        //订单插入成功 进行扣款
        user.setCoin_total(coin_total-coin);
        user.setEnergy_total(energy_total-energy);
        int update = userService.update(user);
        if (update==0){
            //扣款失败删除订单
            orderService.deleteByOrderCode(order.getOrdercode());
            return ApiResponse.of(999,"操作失败请重试",null);
        }
        //商品进行库存划扣
        product.setStock(stock-1);
        int updata = productService.updata(product);
        if (update==0){
            //扣款失败删除订单
            orderService.deleteByOrderCode(order.getOrdercode());
            //恢复余额扣款
            user.setCoin_total(coin_total);
//            user.setEnergy_total(energy_total);
            userService.update(user);
            return ApiResponse.of(999,"操作失败请重试",null);
        }

        return ApiResponse.ofSuccess(order);
    }

    /**
     * 获取历史订单
     * @param pageBean
     * @return
     */
    @PostMapping(value = "/myOrder")
    public ApiResponse myOrder(@Validated @RequestBody PageBean pageBean){
        PageHelper.startPage(pageBean.getPage(), pageBean.getSize());
        List<Order> list = orderService.listByUid(pageBean.getId());
        PageInfo pageInfo = new PageInfo(list);
        return ApiResponse.ofSuccess(pageInfo);
    }
}
