package com.wl.many_steps.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/9/22 12:16
 * desc   :
 */
public class DateUtils {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(Long lt){
        String res;
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
}
