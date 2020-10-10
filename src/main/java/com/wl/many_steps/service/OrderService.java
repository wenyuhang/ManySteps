package com.wl.many_steps.service;

import com.wl.many_steps.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
