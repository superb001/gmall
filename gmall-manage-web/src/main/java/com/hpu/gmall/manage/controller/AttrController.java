package com.hpu.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hpu.gmall.pojo.PmsBaseAttrInfo;
import com.hpu.gmall.pojo.PmsBaseAttrValue;
import com.hpu.gmall.service.AttrService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ClassName: AttrController
 * @Description: 根据三级目录的id查询属性信息
 * @Author: L7O dachaoliu1@163.com
 * @Date: 2019/12/9 14:37
 * @Version: V1.0
 */
@Controller
@CrossOrigin
public class AttrController {

    @Reference
    AttrService attrService;

    /**
     * @Description: 根据catalog3Id查询属性信息
     */
    @RequestMapping("attrInfoList")
    @ResponseBody
    public List<PmsBaseAttrInfo> attrInfoList(String catalog3Id) {
        List<PmsBaseAttrInfo> pmsBaseAttrInfos = attrService.attrInfoList(catalog3Id);
        return pmsBaseAttrInfos;
    }
    /**
     * @Description: 添加属性值
     */
    @RequestMapping("saveAttrInfo")
    @ResponseBody
    public String saveAttrInfo(@RequestBody PmsBaseAttrInfo pmsBaseAttrInfo){

        return "success";
    }


    @RequestMapping("getAttrValueList")
    @ResponseBody
    public List<PmsBaseAttrValue> getAttrValueList(String attrId){
        List<PmsBaseAttrValue> pmsBaseAttrValues = attrService.getAttrValueList(attrId);

        return pmsBaseAttrValues;
    }


}
