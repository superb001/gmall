package com.hpu.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hpu.gmall.manage.util.PmsUploadUtil;
import com.hpu.gmall.manage.util.QiNiuPmsUploadUtil;
import com.hpu.gmall.pojo.PmsProductImage;
import com.hpu.gmall.pojo.PmsProductInfo;
import com.hpu.gmall.pojo.PmsProductSaleAttr;
import com.hpu.gmall.service.SpuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @ClassName: SpuController
 * @Description: 商品spu信息API
 * @Author: L7O dachaoliu1@163.com
 * @Date: 2019/12/11 21:30
 * @Version: V1.0
 */
@Controller
@CrossOrigin
public class SpuController {

    @Reference
    SpuService spuService;

    /**
     * @Description: 查询spu信息列表
     */
    @RequestMapping("spuList")
    @ResponseBody
    public List<PmsProductInfo> spuList(String catalog3Id) {
        List<PmsProductInfo> pmsProductInfos = spuService.spuList(catalog3Id);
        return pmsProductInfos;
    }
    /**
     * @Description: 图片上传
     */
    @RequestMapping("fileUpload")
    @ResponseBody
    public String fileUpload(@RequestParam("file") MultipartFile multipartFile) {
        // @RequestParam("file") MultipartFile multipartFile   接收前端传来的二进制流
        // 将图片或者音视频上传到分布式的文件存储系统,返回资源url路径
        String imgUrl = PmsUploadUtil.uploadImage(multipartFile);
        // String imgUrl = QiNiuPmsUploadUtil.uploadImage(multipartFile);
        // 将图片的存储路径返回给页面
        return imgUrl;
    }

    /**
     * @Description: 保存spu的信息，一次页面只能输入一条，所以参数为pmsProductInfo  JSON对象 <前端查不到，猜的>
     */
    @RequestMapping("saveSpuInfo")
    @ResponseBody
    public String saveSpuInfo(@RequestBody PmsProductInfo pmsProductInfo){
        spuService.saveSpuInfo(pmsProductInfo);
        return "success";
    }


    /**
     * @Description: 根据商品id查询销售属性
     */
    @RequestMapping("spuSaleAttrList")
    @ResponseBody
    public List<PmsProductSaleAttr> spuSaleAttrList(String spuId){  //spuId就是proudctId
        List<PmsProductSaleAttr> pmsProductSaleAttrs = spuService.spuSaleAttrList(spuId);
        return pmsProductSaleAttrs;
    }

    /**
     * @Description: 根据商品id查询平台图片信息
     */
    @RequestMapping("spuImageList")
    @ResponseBody
    public List<PmsProductImage> spuImageList(String spuId){  //spuId就是proudctId
        List<PmsProductImage> pmsProductImages = spuService.spuImageList(spuId);
        return pmsProductImages;
    }







}
