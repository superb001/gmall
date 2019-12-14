package com.hpu.gmall.service;

import com.hpu.gmall.pojo.PmsProductImage;
import com.hpu.gmall.pojo.PmsProductInfo;
import com.hpu.gmall.pojo.PmsProductSaleAttr;

import java.util.List;

/**
 * @ClassName: SpuService
 * @Description: TODO
 * @Author: L7O dachaoliu1@163.com
 * @Date: 2019/12/11 22:52
 * @Version: V1.0
 */
public interface SpuService {

    /**
     * @Description: 根据catalog3Id查询spu的系列商品信息
     */
    List<PmsProductInfo> spuList(String catalog3Id);
    /**
     * @Description: 保存spu信息：系列商品信息
     */
    void saveSpuInfo(PmsProductInfo pmsProductInfo);
    /**
     * @Description: 根据商品id查询销售属性
     */
    List<PmsProductSaleAttr> spuSaleAttrList(String spuId);
    /**
     * @Description: 根据商品id查询平台图片信息
     */
    List<PmsProductImage> spuImageList(String spuId);
}
