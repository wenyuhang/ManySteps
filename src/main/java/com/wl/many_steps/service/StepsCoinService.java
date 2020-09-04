package com.wl.many_steps.service;

import com.wl.many_steps.mapper.StepsCoinMapper;
import com.wl.many_steps.pojo.StepsCoin;
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
public class StepsCoinService {
    @Autowired
    StepsCoinMapper stepsCoinMapper;

    /**
     * 添加步数记录
     * @param stepsCoin
     * @return
     */
    public int add(StepsCoin stepsCoin){
        int save = stepsCoinMapper.add(stepsCoin);
        return save;
    }

    /**
     * 删除步数记录
     * @param id
     */
    public void delete(int id){
        stepsCoinMapper.delete(id);
    }

    /**
     * 更新步数记录
     * @param stepsCoin
     * @return
     */
    public int update(StepsCoin stepsCoin){
        int save = stepsCoinMapper.update(stepsCoin);
        return save;
    }

    /**
     * 获取步数记录
     * @param createdate 以天为单位做计算
     * @return
     */
    public StepsCoin get(int uid,String createdate){
        StepsCoin stepsRecord = null;
//        try {
//            stepsRecord = stepsRecordDAO.findByUserAndCreatedate(user,createdate);
//            if (null==stepsRecord|| TextUtils.isEmpty(stepsRecord.getCreatedate())){
//                stepsRecord = null;
//            }
//        }catch (Exception e){
//            stepsRecord = null;
//        }
        return stepsRecord;
    }

    /**
     * 获取步数记录
     * @param id
     * @return
     */
    public List<StepsCoin> list(int id){
        return stepsCoinMapper.listByUser(id);
    }

}
