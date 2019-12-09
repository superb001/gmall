package com.hpu.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hpu.gmall.manage.mapper.PmsBaseCatalog1Mapper;
import com.hpu.gmall.manage.mapper.PmsBaseCatalog2Mapper;
import com.hpu.gmall.manage.mapper.PmsBaseCatalog3Mapper;
import com.hpu.gmall.pojo.PmsBaseCatalog1;
import com.hpu.gmall.pojo.PmsBaseCatalog2;
import com.hpu.gmall.pojo.PmsBaseCatalog3;
import com.hpu.gmall.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ClassName: CatalogServiceImpl
 * @Description: TODO
 * @Author: L7O dachaoliu1@163.com
 * @Date: 2019/12/9 10:47
 * @Version: V1.0
 */
@Service
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    PmsBaseCatalog1Mapper pmsBaseCatalog1Mapper;
    @Autowired
    PmsBaseCatalog2Mapper pmsBaseCatalog2Mapper;
    @Autowired
    PmsBaseCatalog3Mapper pmsBaseCatalog3Mapper;


    /**
     * @Description: 查询商品目录一的信息
     */
    @Override
    public List<PmsBaseCatalog1> getCatalog1() {
        return pmsBaseCatalog1Mapper.selectAll();

        //等价于下面的动态sql
        /*PmsBaseCatalog1 pmsBaseCatalog1 = new PmsBaseCatalog1();
        return pmsBaseCatalog1Mapper.select(pmsBaseCatalog1);*/
    }

    /**
     * @param catalog1Id
     * @Description: 根据catalog1Id外键，查询目录二的信息
     */
    @Override
    public List<PmsBaseCatalog2> getCatalog2(String catalog1Id) {
        PmsBaseCatalog2 pmsBaseCatalog2 = new PmsBaseCatalog2();
        //外键赋值
        pmsBaseCatalog2.setCatalog1Id(catalog1Id);
        return pmsBaseCatalog2Mapper.select(pmsBaseCatalog2);
    }

    /**
     * @param catalog2Id
     * @Description: 根据catalog2Id，查询目录三的信息
     */
    @Override
    public List<PmsBaseCatalog3> getCatalog3(String catalog2Id) {
        PmsBaseCatalog3 pmsBaseCatalog3 = new PmsBaseCatalog3();
        //外键赋值
        pmsBaseCatalog3.setCatalog2Id(catalog2Id);
        return pmsBaseCatalog3Mapper.select(pmsBaseCatalog3);
    }
}
