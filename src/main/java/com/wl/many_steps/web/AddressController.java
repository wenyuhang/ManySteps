package com.wl.many_steps.web;

import com.wl.many_steps.model.ApiResponse;
import com.wl.many_steps.pojo.Address;
import com.wl.many_steps.pojo.ReqIDBean;
import com.wl.many_steps.pojo.User;
import com.wl.many_steps.service.AddressService;
import com.wl.many_steps.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/9/4 12:09
 * desc   :
 */
@Validated
@RestController
@RequestMapping(value = "/userinfo")
public class AddressController {
    @Autowired
    AddressService addressService;
    @Autowired
    UserService userService;

    /**
     * 添加收货地址
     * @param address
     * @return
     */
    @RequestMapping(value = "/addAddress",method = RequestMethod.POST)
    public ApiResponse add(@Validated @RequestBody Address address){
        //检查用户是否存在
        User user = userService.get(address.getUid());
        if (null==user){
            return ApiResponse.of(999,"该用户不存在",null);
        }
        //检查该地址是否存在
        Address ads = addressService.getByUid(address.getUid());
        if (null!=ads){
            return ApiResponse.of(999,"收货地址已存在，请勿多次添加",null);
        }
        //添加收货地址
        address.setCreatedate(String.valueOf(System.currentTimeMillis()));
        int code = addressService.add(address);
        if (code==0){
            return ApiResponse.of(999,"操作失败请重试",null);
        }
        return ApiResponse.ofSuccess(address);
    }

    /**
     * 删除收货地址
     * @param req
     * @return
     */
    @RequestMapping(value = "/deleteAddress",method = RequestMethod.POST)
    public ApiResponse delete(@Validated @RequestBody ReqIDBean req){
        int code = addressService.delete(req.getId());
        if (code==0){
            return ApiResponse.of(999,"操作失败请重试",null);
        }
        return ApiResponse.ofSuccess(null);
    }

    /**
     * 修改地址
     * @param req
     * @return
     */
    @RequestMapping(value = "/updateAddress",method = RequestMethod.POST)
    public ApiResponse update(@Validated @RequestBody Address req){
        //检查该地址是否存在
        Address address = addressService.getByUid(req.getUid());
        if (null==address){
            return ApiResponse.of(999,"参数错误请重试",null);
        }
        address = req;
        address.setCreatedate(String.valueOf(System.currentTimeMillis()));
        int updata = addressService.updata(address);
        if (updata==0){
            return ApiResponse.of(999,"操作失败请重试",null);
        }
        return ApiResponse.ofSuccess(address);
    }

    /**
     * 获取收货地址
     * @param req
     * @return
     */
    @RequestMapping(value = "/getAddress",method = RequestMethod.POST)
    public ApiResponse get(@Validated @RequestBody ReqIDBean req){
        //检查该地址是否存在
        Address address = addressService.getByUid(req.getId());
        if (null==address){
            return ApiResponse.of(999,"该用户没有添加收货地址",null);
        }
        return ApiResponse.ofSuccess(address);
    }
}
