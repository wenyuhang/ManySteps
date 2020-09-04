package com.wl.many_steps.service;

import com.wl.many_steps.mapper.AddressMapper;
import com.wl.many_steps.pojo.Address;
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
public class AddressService {
    @Autowired
    AddressMapper addressMapper;

    /**
     * 添加用户收货地址
     * @param address
     * @return
     */
    public int add(Address address){
        return addressMapper.add(address);
    }

    /**
     * 删除用户收货地址
     * @param uid
     */
    public int delete(int uid){
       return addressMapper.delete(uid);
    }

    /**
     * 修改用户收货地址
     * @param address
     * @return
     */
    public int updata(Address address){
        return addressMapper.update(address);
    }

    /**
     * 根据uid值获取用户收货地址
     * @param uid 用户id
     * @return
     */
    public Address get(int uid){
        Address address = null;
        try {
            address = addressMapper.get(uid);
            if (null==address|| TextUtils.isEmpty(address.getReceiver())){
                address = null;
            }
        }catch (Exception e){
            address = null;
        }
        return address;
    }



    /**
     * 获取用户收货地址列表
     * @return
     */
    public List<Address> list(){
        return addressMapper.list();
    }
}
