package com.hpu.gmall.user.service.impl;

import com.hpu.gmall.pojo.UmsMember;
import com.hpu.gmall.pojo.UmsMemberReceiveAddress;
import com.hpu.gmall.service.UserService;
import com.hpu.gmall.user.mapper.UmsMemberReceiveAddressMapper;
import com.hpu.gmall.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @ClassName: UserServiceImpl
 * @Description: TODO
 * @Author: L7O dachaoliu1@163.com
 * @Date: 2019/12/5 10:14
 * @Version: V1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    UmsMemberReceiveAddressMapper umsMemberReceiveAddressMapper;

    /**
     * @Description: 查询UmsMember全部数据
     */
    @Override
    public List<UmsMember> getAllUser() {
        // return userMapper.selectAllUser();
        return userMapper.selectAll();
    }

    /**
     * @param memberId
     * @Description: 根据memberId查询UmsMemberReceiveAddress的收货人信息
     */
    @Override
    public List<UmsMemberReceiveAddress> getReceiveAddressByMemberId(String memberId) {
        //return userMapper.selectReceiveAddressByMemberId(memberId);

        // 封装的参数对象
        UmsMemberReceiveAddress umsMemberReceiveAddress = new UmsMemberReceiveAddress();
        umsMemberReceiveAddress.setMemberId(memberId);
        //select方法会根据封装对象中不为空的属性查询
        List<UmsMemberReceiveAddress> umsMemberReceiveAddresses = umsMemberReceiveAddressMapper.select(umsMemberReceiveAddress);


        /*//通过泛型创建封装查询条件参数的对象
        Example example = new Example(UmsMemberReceiveAddress.class);
        //正则条件的属性名必须和类的属性一致
        example.createCriteria().andEqualTo("memberId",memberId);
        //查询方法根据对象中不为空的属性查询并返回结果
        List<UmsMemberReceiveAddress> umsMemberReceiveAddresses = umsMemberReceiveAddressMapper.selectByExample(example);*/

        return umsMemberReceiveAddresses;

    }
}
