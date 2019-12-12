package com.hpu.gmall.service;

import com.hpu.gmall.pojo.PmsProductInfo;

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
}
