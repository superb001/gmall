package com.hpu.gmall.user.mapper;

import com.hpu.gmall.pojo.UmsMember;
import com.hpu.gmall.pojo.UmsMemberReceiveAddress;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<UmsMember> {
    List<UmsMember> selectAllUser();

    List<UmsMemberReceiveAddress> selectReceiveAddressByMemberId(String memberId);
}
