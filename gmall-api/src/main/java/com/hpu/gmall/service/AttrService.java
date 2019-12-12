package com.hpu.gmall.service;

import com.hpu.gmall.pojo.PmsBaseAttrInfo;
import com.hpu.gmall.pojo.PmsBaseAttrValue;
import com.hpu.gmall.pojo.PmsBaseSaleAttr;

import java.util.List;

public interface AttrService {
    /**
     * @Description: 根据catalog3Id查属性信息
     */
    List<PmsBaseAttrInfo> attrInfoList(String catalog3Id);
    /**
     * @Description: 根据attrId查属性值信息
     */
    List<PmsBaseAttrValue> getAttrValueList(String attrId);
    /**
     * @Description: 保存属性和属性值两张表
     */
    String saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo);
    /**
     * @Description: 查询销售属性
     */
    List<PmsBaseSaleAttr> baseSaleAttrList();
}
