package com.hpu.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hpu.gmall.manage.mapper.PmsSkuAttrValueMapper;
import com.hpu.gmall.manage.mapper.PmsSkuImageMapper;
import com.hpu.gmall.manage.mapper.PmsSkuInfoMapper;
import com.hpu.gmall.manage.mapper.PmsSkuSaleAttrValueMapper;
import com.hpu.gmall.pojo.PmsSkuAttrValue;
import com.hpu.gmall.pojo.PmsSkuImage;
import com.hpu.gmall.pojo.PmsSkuInfo;
import com.hpu.gmall.pojo.PmsSkuSaleAttrValue;
import com.hpu.gmall.service.SkuServer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ClassName: SkuServiceImpl
 * @Description: TODO
 * @Author: L7O dachaoliu1@163.com
 * @Date: 2019/12/14 22:43
 * @Version: V1.0
 */
@Service
public class SkuServiceImpl implements SkuServer {

    @Autowired
    PmsSkuInfoMapper pmsSkuInfoMapper;
    @Autowired
    PmsSkuImageMapper pmsSkuImageMapper;
    @Autowired
    PmsSkuAttrValueMapper pmsSkuAttrValueMapper;
    @Autowired
    PmsSkuSaleAttrValueMapper pmsSkuSaleAttrValueMapper;

    /**
     * @param pmsSkuInfo
     * @Description: 保存具体的商品信息
     */
    @Override
    public void saveSkuInfo(PmsSkuInfo pmsSkuInfo) {

        // 保存商品信息
        int i = pmsSkuInfoMapper.insertSelective(pmsSkuInfo);
        String skuId = pmsSkuInfo.getId();

        // 保存图片信息:插入图片信息
        List<PmsSkuImage> skuImageList = pmsSkuInfo.getSkuImageList();
        for (PmsSkuImage pmsSkuImage : skuImageList) {
            pmsSkuImage.setSkuId(skuId);
            pmsSkuImageMapper.insertSelective(pmsSkuImage);
        }

        // 保存关联平台属性表:插入平台属性关联
        List<PmsSkuAttrValue> skuAttrValueList = pmsSkuInfo.getSkuAttrValueList();
        for (PmsSkuAttrValue pmsSkuAttrValue : skuAttrValueList) {
            pmsSkuAttrValue.setSkuId(skuId);
            pmsSkuAttrValueMapper.insertSelective(pmsSkuAttrValue);
        }

        // 保存关联销售属性表:插入销售属性关联
        List<PmsSkuSaleAttrValue> skuSaleAttrValueList = pmsSkuInfo.getSkuSaleAttrValueList();
        for (PmsSkuSaleAttrValue pmsSkuSaleAttrValue : skuSaleAttrValueList) {
            pmsSkuSaleAttrValue.setSkuId(skuId);
            pmsSkuSaleAttrValueMapper.insertSelective(pmsSkuSaleAttrValue);

        }

    }
}
