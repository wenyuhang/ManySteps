package com.wl.many_steps.web;

import org.springframework.web.bind.annotation.RestController;


/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/8/17 17:12
 * desc   :
 */
@RestController
public class StepsRecordController {
//    @Autowired
//    StepsRecordService stepsRecordService;
//    @Autowired
//    UserService userService;
//
//    private  SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
//
//    @RequestMapping(value = "/addStepsRecord",method = RequestMethod.POST)
//    public ApiResponse add(String uid,String steps){
//        if (TextUtils.isEmpty(uid)){
//            return ApiResponse.of(999,"uid参数不能为空",null);
//        }
//        if (TextUtils.isEmpty(steps)){
//            return ApiResponse.of(999,"steps参数不能为空",null);
//        }
//        try {
//            Integer.parseInt(steps);
//        }catch (Exception e){
//            return ApiResponse.of(999,"steps参数错误",null);
//        }
//        //校验获取user对象
//        User user = null;
//        try {
//            user = userService.get(Integer.parseInt(uid));
//            if (null==user){
//                throw new Exception();
//            }
//        }catch (Exception e){
//            return ApiResponse.of(999,"uid参数错误，没有此用户",null);
//        }
//        //获取当前为哪天
//        String date = format.format(System.currentTimeMillis());
//
//        StepsRecord stepsRecord = stepsRecordService.get(user,date);
//        if (null==stepsRecord){
//            stepsRecord = new StepsRecord();
//            stepsRecord.setUser(user);
//            stepsRecord.setCreatedate(date);
//            stepsRecord.setSteps(Integer.parseInt(steps));
//        }else {
//            stepsRecord.setSteps(Integer.parseInt(steps));
//        }
//        stepsRecord = stepsRecordService.add(stepsRecord);
//
//        return ApiResponse.ofSuccess(stepsRecord);
//    }
//
//    @RequestMapping(value = "/exchangeSteps",method = RequestMethod.POST)
//    public ApiResponse exchange(String uid,String steps){
//        if (TextUtils.isEmpty(uid)){
//            return ApiResponse.of(999,"uid参数不能为空",null);
//        }
//        if (TextUtils.isEmpty(steps)){
//            return ApiResponse.of(999,"steps参数不能为空",null);
//        }
//        try {
//            Integer.parseInt(steps);
//        }catch (Exception e){
//            return ApiResponse.of(999,"steps参数错误",null);
//        }
//        //校验获取user对象
//        User user = null;
//        try {
//            user = userService.get(Integer.parseInt(uid));
//            if (null==user){
//                throw new Exception();
//            }
//        }catch (Exception e){
//            return ApiResponse.of(999,"uid参数错误，没有此用户",null);
//        }
//        //获取当前为哪天
//        String date = format.format(System.currentTimeMillis());
//
//        StepsRecord stepsRecord = stepsRecordService.get(user,date);
//        int convertSteps = Integer.parseInt(steps)-stepsRecord.getConvertsteps();
//        if (null==stepsRecord){
//            stepsRecord = new StepsRecord();
//            stepsRecord.setUser(user);
//            stepsRecord.setCreatedate(date);
//        }
//        stepsRecord.setSteps(Integer.parseInt(steps));
//        stepsRecord.setConvertsteps(convertSteps);
//        stepsRecord = stepsRecordService.update(stepsRecord);
//
//        return ApiResponse.ofSuccess(stepsRecord);
//    }

}
