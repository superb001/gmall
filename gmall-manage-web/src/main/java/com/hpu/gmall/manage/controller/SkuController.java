package com.hpu.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hpu.gmall.pojo.PmsProductInfo;
import com.hpu.gmall.pojo.PmsSkuInfo;
import com.hpu.gmall.service.SkuServer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ClassName: SkuController
 * @Description: 商品sku信息API
 * @Author: L7O dachaoliu1@163.com
 * @Date: 2019/12/14 21:41
 * @Version: V1.0
 */
@Controller
@CrossOrigin
public class SkuController {

    @Reference
    SkuServer skuServer;
    /**
     * @Description: 保存具体的商品信息
     */
    @RequestMapping("saveSkuInfo")
    @ResponseBody
    public String saveSkuInfo(@RequestBody PmsSkuInfo pmsSkuInfo) {
        // 将spuId封装给productId:解决前后端字段不对应问题
        pmsSkuInfo.setProductId(pmsSkuInfo.getSpuId());

        // 处理默认图片
        String skuDefaultImg = pmsSkuInfo.getSkuDefaultImg();
        if(StringUtils.isBlank(skuDefaultImg)){
            // 如果没设置默认图片，将pms_sku_image对象的第一行设为默认url值
            pmsSkuInfo.setSkuDefaultImg(pmsSkuInfo.getSkuImageList().get(0).getImgUrl());
        }

        skuServer.saveSkuInfo(pmsSkuInfo);
        return "success";

    }



}
