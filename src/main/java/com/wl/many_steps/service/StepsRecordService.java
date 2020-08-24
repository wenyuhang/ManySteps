package com.wl.many_steps.service;

import org.springframework.stereotype.Service;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/8/17 16:52
 * desc   :
 */
@Service
public class StepsRecordService{
//    @Autowired
//    StepsRecordMapper stepsRecordDAO;
//
//    /**
//     * 添加步数记录
//     * @param stepsRecord
//     * @return
//     */
//    public StepsRecord add(StepsRecord stepsRecord){
//        StepsRecord save = stepsRecordDAO.save(stepsRecord);
//        return stepsRecord;
//    }
//
//    /**
//     * 删除步数记录
//     * @param id
//     */
//    public void delete(int id){
//        stepsRecordDAO.deleteById(id);
//    }
//
//    /**
//     * 更新步数记录
//     * @param stepsRecord
//     * @return
//     */
//    public StepsRecord update(StepsRecord stepsRecord){
//        StepsRecord save = stepsRecordDAO.save(stepsRecord);
//        return save;
//    }
//
//    /**
//     * 获取步数记录
//     * @param createdate 以天为单位做计算
//     * @return
//     */
//    public StepsRecord get(User user,String createdate){
//        StepsRecord stepsRecord = null;
//        try {
//            stepsRecord = stepsRecordDAO.findByUserAndCreatedate(user,createdate);
//            if (null==stepsRecord|| TextUtils.isEmpty(stepsRecord.getCreatedate())){
//                stepsRecord = null;
//            }
//        }catch (Exception e){
//            stepsRecord = null;
//        }
//        return stepsRecord;
//    }

}
