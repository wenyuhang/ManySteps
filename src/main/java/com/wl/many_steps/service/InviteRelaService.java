package com.wl.many_steps.service;

import com.wl.many_steps.mapper.InviteRelaMapper;
import com.wl.many_steps.pojo.InviteRela;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/10/14 16:08
 * desc   :
 */
@Service
public class InviteRelaService {
    @Autowired
    InviteRelaMapper inviteRelaMapper;

    public int add(InviteRela inviteRela){
        return inviteRelaMapper.add(inviteRela);
    }

    public int delete(int id){
        return inviteRelaMapper.delete(id);
    }

    public int update(InviteRela inviteRela){
        return inviteRelaMapper.update(inviteRela);
    }

    public InviteRela get(int id){
        InviteRela result = null;
        try {
            result = inviteRelaMapper.get(id);
            if (null==result|| TextUtils.isEmpty(result.getCreatedate())){
                result = null;
            }
        }catch (Exception e){
            result = null;
        }
        return result;

    }

    public List<InviteRela> getByUid(int uid){
        return inviteRelaMapper.getByUid(uid);
    }

    public List<InviteRela> getByInviteid(int inviteid){
        return inviteRelaMapper.getByInviteid(inviteid);
    }
}
