package com.hpu.gmall.user.service;

import com.hpu.gmall.user.pojo.UmsMember;
import com.hpu.gmall.user.pojo.UmsMemberReceiveAddress;

import java.util.List;

public interface UserService {
    /**
     * @Description: 查询UmsMember全部数据
     */
    List<UmsMember> getAllUser();
    /**
     * @Description: 根据memberId查询UmsMemberReceiveAddress的收货人信息
     */
    List<UmsMemberReceiveAddress> getReceiveAddressByMemberId(String memberId);
}
