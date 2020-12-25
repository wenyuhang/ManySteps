package com.wl.many_steps.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wl.many_steps.model.ApiResponse;
import com.wl.many_steps.pojo.NoticesRecord;
import com.wl.many_steps.pojo.PageBean;
import com.wl.many_steps.pojo.ReqIDBean;
import com.wl.many_steps.service.NoticesRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/12/14 17:04
 * desc   :
 */
@RestController
@RequestMapping(value = "/notices")
public class NoticesRecordController {
    @Autowired
    NoticesRecordService noticesRecordService;

    /**
     * 获取用户公告记录
     * @param reqIDBean
     * @return
     */
    @PostMapping(value = "/getUserNoticesRecord")
    public ApiResponse getUserRecord(@Validated @RequestBody ReqIDBean reqIDBean){
        List<NoticesRecord> userRecord = noticesRecordService.getUserNotices(reqIDBean.getId());
        NoticesRecord noticesRecord = null;
        if(null!=userRecord && userRecord.size()>0){
            noticesRecord = userRecord.get(userRecord.size() - 1);
            noticesRecord.setP_status(true);
            noticesRecord.setUpdate_time(new Timestamp(System.currentTimeMillis()));
            noticesRecordService.update(noticesRecord);
        }
        return ApiResponse.ofSuccess(noticesRecord);
    }

    /**
     * 处罚记录查询
     * @param pageBean
     * @return
     */
    @PostMapping(value = "/getNoticesRecord")
    public ApiResponse list(@Validated @RequestBody PageBean pageBean){
        PageHelper.startPage(pageBean.getPage(),pageBean.getSize());
        List<NoticesRecord> list = noticesRecordService.list();
        PageInfo pageInfo=new PageInfo(list);
        return ApiResponse.ofSuccess(pageInfo);
    }
}
