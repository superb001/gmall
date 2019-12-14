package com.hpu.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hpu.gmall.pojo.PmsBaseCatalog1;
import com.hpu.gmall.pojo.PmsBaseCatalog2;
import com.hpu.gmall.pojo.PmsBaseCatalog3;
import com.hpu.gmall.service.CatalogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ClassName: CatalogController
 * @Description: 三级目录的查询接口
 * @Author: L7O dachaoliu1@163.com
 * @Date: 2019/12/9 10:26
 * @Version: V1.0
 */
@Controller
@CrossOrigin
public class CatalogController {

    @Reference
    CatalogService catalogService;

    /**
     * @Description: 查看一级目录
     */
    @RequestMapping("getCatalog1")
    @ResponseBody
    public List<PmsBaseCatalog1> getCatalog1() {
        List<PmsBaseCatalog1> pmsBaseCatalog1List = catalogService.getCatalog1();
        return pmsBaseCatalog1List; }

    /**
     * @Description: 查看二级目录
     */
    @RequestMapping("getCatalog2")
    @ResponseBody
    public List<PmsBaseCatalog2> getCatalog2(String catalog1Id) {
        List<PmsBaseCatalog2> pmsBaseCatalog2List = catalogService.getCatalog2(catalog1Id);
        return pmsBaseCatalog2List;
    }

    /**
     * @Description: 查看三级目录
     */
    @RequestMapping("getCatalog3")
    @ResponseBody
    public List<PmsBaseCatalog3> getCatalog3(String catalog2Id) {
        List<PmsBaseCatalog3> pmsBaseCatalog3List = catalogService.getCatalog3(catalog2Id);
        return pmsBaseCatalog3List;
    }
}
