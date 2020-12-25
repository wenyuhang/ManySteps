package com.wl.many_steps.service;

import com.wl.many_steps.mapper.NoticeRecordMapper;
import com.wl.many_steps.pojo.NoticesRecord;
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
public class NoticesRecordService {

    @Autowired
    NoticeRecordMapper noticeRecordMapper;

    /**
     * 查询用户是否含有未读公告
     *
     * @param uid
     * @return
     */
    public boolean isHaveNotices(int uid) {
        try {
            int noticesCount = noticeRecordMapper.getUserNoticesCount(uid);
            if (noticesCount > 0) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * 修改用户公告记录
     *
     * @param noticesRecord
     * @return
     */
    public int update(NoticesRecord noticesRecord) {
        return noticeRecordMapper.update(noticesRecord);
    }

    /**
     * 获取用户处罚记录
     *
     * @param uid
     * @return
     */
    public List<NoticesRecord> getUserNotices(int uid) {
        return noticeRecordMapper.getUserNotices(uid);
    }

    /**
     * 获取全部公告记录
     *
     * @return
     */
    public List<NoticesRecord> list() {
        return noticeRecordMapper.list();
    }
}
