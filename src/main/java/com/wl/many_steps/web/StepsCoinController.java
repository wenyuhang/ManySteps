package com.wl.many_steps.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wl.many_steps.model.ApiResponse;
import com.wl.many_steps.pojo.PageBean;
import com.wl.many_steps.pojo.StepsCoin;
import com.wl.many_steps.pojo.StepsRecord;
import com.wl.many_steps.service.StepsCoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.List;


/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/8/17 17:12
 * desc   :
 */
@Validated
@RestController
@RequestMapping("/userinfo")
public class StepsCoinController {
    @Autowired
    StepsCoinService stepsCoinService;



    /**
     * 获取用户金币兑换记录
     * @param pageBean
     * @return
     */
    @RequestMapping(value = "/coinRecord", method = RequestMethod.POST)
    public ApiResponse list(@Validated @RequestBody PageBean pageBean) {
        PageHelper.startPage(pageBean.getPage(), pageBean.getSize());
        List<StepsCoin> list = stepsCoinService.list(pageBean.getId());
        PageInfo pageInfo = new PageInfo(list);
        return ApiResponse.ofSuccess(pageInfo);
    }

}
