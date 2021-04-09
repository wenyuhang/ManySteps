package com.wl.many_steps.service;

import com.wl.many_steps.mapper.VersionReviewMapper;
import com.wl.many_steps.pojo.VersionReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/12/14 17:00
 * desc   :
 */
@Service
public class VersionReviewService {

    @Autowired
    VersionReviewMapper versionReviewMapper;


    /**
     * 获取审核版本
     * @return
     */
    public VersionReview list() {
        List<VersionReview> list = versionReviewMapper.list();
        if (list!=null && list.size()>0){
            return list.get(0);
        }else {
         return null;
        }
    }
}
