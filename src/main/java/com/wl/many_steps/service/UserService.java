package com.wl.many_steps.service;

import com.wl.many_steps.mapper.UserMapper;
import com.wl.many_steps.pojo.User;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/8/17 15:46
 * desc   :
 */
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    /**
     * 添加用户
     * @param user
     * @return
     */
    public int add(User user){
        int save = userMapper.add(user);
        return save;
    }

    /**
     * 删除用户
     * @param id
     */
    public void delete(int id){
        userMapper.delete(id);
    }

    /**
     * 更新用户
     * @param user
     * @return
     */
    public int update(User user){
        int save = userMapper.update(user);
        return save;
    }

    /**
     * 获取用户
     * @param openid 微信用户openid
     * @return
     */
    public User get(String openid){
        User user = null;
        try {
            user = userMapper.getByOpenid(openid);
            if (null==user|| TextUtils.isEmpty(user.getOpenid())){
                user = null;
            }
        }catch (Exception e){
            user = null;
        }
        return user;
    }


    /**
     * 获取用户
     * @param id 用户id
     * @return
     */
    public User get(int id){
        User user = null;
        try {
            user = userMapper.get(id);
            if (null==user|| TextUtils.isEmpty(user.getOpenid())){
                user = null;
            }
        }catch (Exception e){
            user = null;
        }
        return user;
    }

    /**
     * 获取用户注册数
     * @return
     */
    public int getUserCount(){
        return userMapper.getUserCount();
    }

    /**
     * 获取用户列表
     * @return
     */
    public List<User> list(){
        List<User> list = userMapper.list();
        return list;
    }

    /**
     * 获取步数排行榜
     * @return
     */
    public List<User> getStepsRankList(){
        List<User> list = userMapper.stepsRankList();
        return list;
    }

    /**
     *  获取用户步数排位名次
     * @param id
     * @return
     */
    public int getUserStepsRanking(int id){
        int userRanking = userMapper.getUserStepsRanking(id);
        return userRanking;
    }

    /**
     * 获取邀请排行榜
     * @return
     */
    public List<User> getInviteRankList(){
        List<User> list = userMapper.inviteRankList();
        return list;
    }

    /**
     * 获取用户邀请排位名字
     * @param id
     * @return
     */
    public int getUserInviteRanking(int id){
        int userInviteRank = userMapper.getUserInviteRank(id);
        return userInviteRank;
    }
}
