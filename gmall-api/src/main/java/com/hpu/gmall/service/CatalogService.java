package com.hpu.gmall.service;

import com.hpu.gmall.pojo.PmsBaseCatalog1;
import com.hpu.gmall.pojo.PmsBaseCatalog2;
import com.hpu.gmall.pojo.PmsBaseCatalog3;

import java.util.List;

public interface CatalogService {
    /**
     * @Description: 查询目录一的信息
     */
    List<PmsBaseCatalog1> getCatalog1();

    /**
     * @Description: 根据catalog1Id，查询目录二的信息
     */
    List<PmsBaseCatalog2> getCatalog2(String catalog1Id);
    /**
     * @Description: 根据catalog2Id，查询目录三的信息
     */
    List<PmsBaseCatalog3> getCatalog3(String catalog2Id);
}
