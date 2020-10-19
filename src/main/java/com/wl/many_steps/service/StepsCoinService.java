package com.wl.many_steps.service;

import com.wl.many_steps.mapper.StepsCoinMapper;
import com.wl.many_steps.pojo.StepsCoin;
import com.wl.many_steps.utils.CoinUtils;
import com.wl.many_steps.utils.DateUtils;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
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
    private SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

    /**
     * 添加金币交易记录
     * @param
     * @return
     */
    public int add(int uid,String tran_desc,float coin,int convertsteps){
        StepsCoin stepsCoin = new StepsCoin();
        stepsCoin.setUid(uid);
        stepsCoin.setTran_desc(tran_desc);
        stepsCoin.setCoin(coin);
        stepsCoin.setRundate(format.format(System.currentTimeMillis()));
        stepsCoin.setConvertsteps(convertsteps);
        stepsCoin.setCreatedate(DateUtils.stampToDate(System.currentTimeMillis()));
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
     * 计算用户金币总和
     * @param uid
     * @return
     */
    public float sum(int uid){
        return CoinUtils.format(stepsCoinMapper.sumCoin(uid));
    }

    /**
     * 获取步数记录
     * @param createdate 以天为单位做计算
     * @return
     */
    public StepsCoin get(int uid,String createdate){
        StepsCoin stepsRecord = null;
        try {
            stepsRecord = stepsCoinMapper.getDataToday(uid,createdate);
            if (null==stepsRecord|| TextUtils.isEmpty(stepsRecord.getCreatedate())){
                stepsRecord = null;
            }
        }catch (Exception e){
            stepsRecord = null;
        }
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
