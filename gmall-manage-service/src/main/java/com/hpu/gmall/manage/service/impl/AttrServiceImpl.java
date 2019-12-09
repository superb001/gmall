package com.hpu.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hpu.gmall.manage.mapper.PmsBaseAttrInfoMapper;
import com.hpu.gmall.manage.mapper.PmsBaseAttrValueMapper;
import com.hpu.gmall.pojo.PmsBaseAttrInfo;
import com.hpu.gmall.pojo.PmsBaseAttrValue;
import com.hpu.gmall.service.AttrService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ClassName: AttrServiceImpl
 * @Description: TODO
 * @Author: L7O dachaoliu1@163.com
 * @Date: 2019/12/9 14:49
 * @Version: V1.0
 */
@Service
public class AttrServiceImpl implements AttrService {

    @Autowired
    PmsBaseAttrInfoMapper pmsBaseAttrInfoMapper;
    @Autowired
    PmsBaseAttrValueMapper pmsBaseAttrValueMapper;

    /**
     * @Description: 根据catalog3Id查询属性信息
     */
    @Override
    public List<PmsBaseAttrInfo> attrInfoList(String catalog3Id) {
        PmsBaseAttrInfo pmsBaseAttrInfo = new PmsBaseAttrInfo();
        pmsBaseAttrInfo.setCatalog3Id(catalog3Id);
        return pmsBaseAttrInfoMapper.select(pmsBaseAttrInfo);
    }

    /**
     * @param attrId
     * @Description: 根据attrId查属性值信息
     */
    @Override
    public List<PmsBaseAttrValue> getAttrValueList(String attrId) {
        PmsBaseAttrValue pmsBaseAttrValue = new PmsBaseAttrValue();
        pmsBaseAttrValue.setAttrId(attrId);
        return pmsBaseAttrValueMapper.select(pmsBaseAttrValue);
    }
}
