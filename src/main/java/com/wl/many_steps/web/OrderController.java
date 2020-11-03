package com.wl.many_steps.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wl.many_steps.model.ApiResponse;
import com.wl.many_steps.pojo.*;
import com.wl.many_steps.service.OrderService;
import com.wl.many_steps.service.ProductService;
import com.wl.many_steps.service.StepsCoinService;
import com.wl.many_steps.service.UserService;
import com.wl.many_steps.utils.DateUtils;
import com.wl.many_steps.utils.RandomNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/10/10 11:49
 * desc   :
 */

@Validated
@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @Autowired
    StepsCoinService stepsCoinService;


    /**
     * 下单前 查询余额
     *
     * @param balanceBean
     * @return
     */
    @PostMapping(value = "/checkBalance")
    public ApiResponse check(@Validated @RequestBody BalanceBean balanceBean) {

        User user = userService.get(balanceBean.getUid());
        if (null == user) {
            return ApiResponse.of(999, "用户不存在", null);
        }
        Product product = productService.get(balanceBean.getPid());
        if (null == product) {
            return ApiResponse.of(999, "商品不存在", null);
        }
        float coin = product.getCoin();
        float energy = product.getEnergy();
        float coin_total = user.getCoin_total();

        if (coin_total < coin) {
            return ApiResponse.of(999, "金币余额不足", null);
        }
        return ApiResponse.ofSuccess(null);
    }

    /**
     * 用户进行下单 扣款
     *
     * @param placeOrderBean
     * @return
     */
    @PostMapping(value = "/placeOrder")
    public ApiResponse placeOrder(@Validated @RequestBody PlaceOrderBean placeOrderBean) {
        //检查余额
        User user = userService.get(placeOrderBean.getUid());
        if (null == user) {
            return ApiResponse.of(999, "用户不存在", null);
        }
        Product product = productService.get(placeOrderBean.getPid());
        if (null == product) {
            return ApiResponse.of(999, "商品不存在", null);
        }
        float coin = product.getCoin();
        float energy = product.getEnergy();
        int stock = product.getStock();
        float coin_total = user.getCoin_total();

        if (stock <= 0) {
            return ApiResponse.of(999, "商品已被兑换完毕，工作人员正在加紧补货！~", null);
        }
        if (coin_total < coin) {
            return ApiResponse.of(999, "金币余额不足", null);
        }
        //创建订单
        Order order = new Order();
        order.setUid(placeOrderBean.getUid());
        order.setPid(placeOrderBean.getPid());
        order.setAdid(placeOrderBean.getAdid());
        order.setOrdercode(RandomNumber.getInstance().GetRandom());
        order.setStatus(10);
        order.setCreatedate(DateUtils.stampToDate(System.currentTimeMillis()));
        int code = orderService.add(order);
        if (code == 0) {
            return ApiResponse.of(999, "操作失败请重试", null);
        }
        //订单插入成功 进行扣款
        stepsCoinService.add(placeOrderBean.getUid(), "购买" + product.getName() + "划扣", -coin, 0);

        //商品进行库存划扣
        product.setStock(stock - 1);
        productService.updata(product);

        return ApiResponse.ofSuccess(order);
    }

    /**
     * 获取单个用户的历史订单
     *
     * @param pageBean
     * @return
     */
    @PostMapping(value = "/myOrder")
    public ApiResponse myOrder(@Validated @RequestBody PageBean pageBean) {
        PageHelper.startPage(pageBean.getPage(), pageBean.getSize());
        List<Order> list = orderService.listByUid(pageBean.getId());
        PageInfo pageInfo = new PageInfo(list);
        return ApiResponse.ofSuccess(pageInfo);
    }

    /**
     * 获取商品列表
     *
     * @param pageBean
     * @return
     */
    @PostMapping(value = "/orderList")
    public ApiResponse list(@Validated @RequestBody PageBean pageBean) {
        PageHelper.startPage(pageBean.getPage(), pageBean.getSize());
        List<Order> list = orderService.list();
        PageInfo pageInfo = new PageInfo(list);
        return ApiResponse.ofSuccess(pageInfo);
    }

    /**
     * 获取商品列表
     *
     * @param pageBean
     * @return
     */
    @PostMapping(value = "/orderListByUser")
    public ApiResponse listByUser(@Validated @RequestBody PageBean pageBean) {
        PageHelper.startPage(pageBean.getPage(), pageBean.getSize());
        List<Order> list = orderService.listByUid(pageBean.getId());
        PageInfo pageInfo = new PageInfo(list);
        return ApiResponse.ofSuccess(pageInfo);
    }

    /**
     * 获取商品列表
     *
     * @param pageBean
     * @return
     */
    @PostMapping(value = "/orderListByProduct")
    public ApiResponse listByProduct(@Validated @RequestBody PageBean pageBean) {
        PageHelper.startPage(pageBean.getPage(), pageBean.getSize());
        List<Order> list = orderService.listByPid(pageBean.getId());
        PageInfo pageInfo = new PageInfo(list);
        return ApiResponse.ofSuccess(pageInfo);
    }
}
