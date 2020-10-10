package com.wl.many_steps.web;

import com.wl.many_steps.model.ApiResponse;
import com.wl.many_steps.pojo.BalanceBean;
import com.wl.many_steps.pojo.Product;
import com.wl.many_steps.pojo.User;
import com.wl.many_steps.service.OrderService;
import com.wl.many_steps.service.ProductService;
import com.wl.many_steps.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        if (energy_total<energy){
            return ApiResponse.of(999,"包邮能量不足",null);
        }
        return ApiResponse.ofSuccess(null);
    }
}
