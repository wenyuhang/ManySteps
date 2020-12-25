package com.wl.many_steps.service;

import com.wl.many_steps.mapper.StepsRecordMapper;
import com.wl.many_steps.pojo.StepsRecord;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/8/17 16:52
 * desc   :
 */
@Service
public class StepsRecordService{
    @Autowired
    StepsRecordMapper stepsRecordMapper;

    /**
     * 添加步数记录
     * @param stepsRecord
     * @return
     */
    public int add(StepsRecord stepsRecord){
        int save = stepsRecordMapper.add(stepsRecord);
        return save;
    }

    /**
     * 删除步数记录
     * @param id
     */
    public void delete(int id){
        stepsRecordMapper.delete(id);
    }

    /**
     * 更新步数记录
     * @param stepsRecord
     * @return
     */
    public int update(StepsRecord stepsRecord){
        int save = stepsRecordMapper.update(stepsRecord);
        return save;
    }

    /**
     * 计算步数总和
     * @param uid
     * @return
     */
    public int sum(int uid){
        return stepsRecordMapper.sumSteps(uid);
    }

    /**
     * 获取步数记录
     * @param rundate 以天为单位做计算
     * @return
     */
    public StepsRecord get(int uid,String rundate){
        StepsRecord stepsRecord = null;
        try {
             stepsRecord = stepsRecordMapper.getByUidAndRundate(uid,rundate);
            if (null==stepsRecord|| TextUtils.isEmpty(stepsRecord.getCreatedate())){
                stepsRecord = null;
            }
        }catch (Exception e){
//            stepsRecord = null;
            System.out.println(e);
        }
        return stepsRecord;
    }

    /**
     * 获取步数记录
     * @param id
     * @return
     */
    public List<StepsRecord> list(int id){
        return stepsRecordMapper.listByUser(id);
    }

    /**
     * 获取存在刷步警告数据
     * @return
     */
    public List<StepsRecord> getMonitorsData(){
        return stepsRecordMapper.getMonitorsData();
    }


    /**
     * 获取用户今日步数排名
     * @param uid
     * @return
     */
    public int getUserTodayRank(int uid){
        int userRanking = 0;
        try {
            userRanking = stepsRecordMapper.getUserTodayRank(uid);
        }catch (Exception e){
            userRanking = 0;
        }
        return userRanking;
    }

    /**
     * 获取用户今日的步数
     * @param uid
     * @return
     */
    public int getUserTodaySteps(int uid){
        int steps = 0;
        try {
            steps = stepsRecordMapper.getUserTodaySteps(uid);
        }catch (Exception e){
            steps = 0;
        }
        return steps;
    }

}
