package com.wl.many_steps.mapper;

import com.wl.many_steps.pojo.VersionReview;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/9/4 11:59
 * desc   :
 */
public interface VersionReviewMapper {

    @Select(" select * from m_version_review ORDER BY update_time DESC")
    List<VersionReview> list();
}
