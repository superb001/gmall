package com.hpu.gmall.user.controller;


import com.hpu.gmall.pojo.UmsMember;
import com.hpu.gmall.pojo.UmsMemberReceiveAddress;
import com.hpu.gmall.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.dubbo.config.annotation.Reference;

import java.util.List;

/**
 * @ClassName: UserController
 * @Description: TODO
 * @Author: L7O dachaoliu1@163.com
 * @Date: 2019/12/5 10:13
 * @Version: V1.0
 */
@Controller
public class UserController {

    //@Autowired
    @Reference
    UserService userService;

    @ResponseBody
    @RequestMapping("index")
    public String index() {
        return "hello user";
    }

    @RequestMapping("getAllUser")
    @ResponseBody
    public List<UmsMember> getAllUser() {
        return userService.getAllUser();
    }

    // http://localhost:8080/getReceiveAddressByMemberId?memberId=1
    @RequestMapping("getReceiveAddressByMemberId")
    @ResponseBody
    public List<UmsMemberReceiveAddress> getReceiveAddressByMemberId(String memberId) {
        return userService.getReceiveAddressByMemberId(memberId);
    }






}
