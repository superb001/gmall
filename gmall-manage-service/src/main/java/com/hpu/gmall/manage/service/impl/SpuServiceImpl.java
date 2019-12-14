package com.hpu.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hpu.gmall.manage.mapper.PmsProductImageMapper;
import com.hpu.gmall.manage.mapper.PmsProductInfoMapper;
import com.hpu.gmall.manage.mapper.PmsProductSaleAttrMapper;
import com.hpu.gmall.manage.mapper.PmsProductSaleAttrValueMapper;
import com.hpu.gmall.pojo.PmsProductImage;
import com.hpu.gmall.pojo.PmsProductInfo;
import com.hpu.gmall.pojo.PmsProductSaleAttr;
import com.hpu.gmall.pojo.PmsProductSaleAttrValue;
import com.hpu.gmall.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ClassName: SpuServiceImpl
 * @Description: 系列商品信息管理：基于PmsProductInfo的四张表
 * @Author: L7O dachaoliu1@163.com
 * @Date: 2019/12/11 22:54
 * @Version: V1.0
 */
@Service
public class SpuServiceImpl implements SpuService {

    @Autowired
    PmsProductInfoMapper pmsProductInfoMapper;

    @Autowired
    PmsProductImageMapper pmsProductImageMapper;

    @Autowired
    PmsProductSaleAttrMapper pmsProductSaleAttrMapper;

    @Autowired
    PmsProductSaleAttrValueMapper pmsProductSaleAttrValueMapper;

    /**
     * @param catalog3Id
     * @Description: 根据catalog3Id查询spu的系列商品信息
     */
    @Override
    public List<PmsProductInfo> spuList(String catalog3Id) {

        PmsProductInfo pmsProductInfo = new PmsProductInfo();
        pmsProductInfo.setCatalog3Id(catalog3Id);

        List<PmsProductInfo> pmsProductInfos = pmsProductInfoMapper.select(pmsProductInfo);
        return pmsProductInfos;
    }

    /**
     * @Description: 保存spu信息：系列商品信息
     */
    @Override
    public void saveSpuInfo(PmsProductInfo pmsProductInfo) {

        // 保存商品信息
        pmsProductInfoMapper.insertSelective(pmsProductInfo);

        // 生成商品主键:spuId
        String productId = pmsProductInfo.getId();

        // 保存商品图片信息
        List<PmsProductImage> spuImageList = pmsProductInfo.getSpuImageList();
        // 保存对象为集合，迭代保存
        for (PmsProductImage pmsProductImage : spuImageList) {
            // 添加外键再保存
            pmsProductImage.setProductId(productId);
            pmsProductImageMapper.insertSelective(pmsProductImage);
        }


        // 保存销售属性信息
        List<PmsProductSaleAttr> spuSaleAttrList = pmsProductInfo.getSpuSaleAttrList();
        for (PmsProductSaleAttr pmsProductSaleAttr : spuSaleAttrList) {
            // 添加外键再保存
            pmsProductSaleAttr.setProductId(productId);
            pmsProductSaleAttrMapper.insertSelective(pmsProductSaleAttr);


            // 保存销售属性值
            List<PmsProductSaleAttrValue> spuSaleAttrValueList = pmsProductSaleAttr.getSpuSaleAttrValueList();
            for (PmsProductSaleAttrValue pmsProductSaleAttrValue : spuSaleAttrValueList) {
                // 添加外键再保存
                pmsProductSaleAttrValue.setProductId(productId);
                pmsProductSaleAttrValueMapper.insertSelective(pmsProductSaleAttrValue);
            }
        }
    }
}
