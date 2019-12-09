package com.hpu.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hpu.gmall.manage.mapper.PmsBaseAttrInfoMapper;
import com.hpu.gmall.manage.mapper.PmsBaseAttrValueMapper;
import com.hpu.gmall.pojo.PmsBaseAttrInfo;
import com.hpu.gmall.pojo.PmsBaseAttrValue;
import com.hpu.gmall.service.AttrService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @ClassName: AttrServiceImpl
 * @Description: TODO
 * @Author: L7O dachaoliu1@163.com
 * @Date: 2019/12/9 14:49
 * @Version: V1.0
 */
@Service
public class AttrServiceImpl implements AttrService {

    @Autowired
    PmsBaseAttrInfoMapper pmsBaseAttrInfoMapper;
    @Autowired
    PmsBaseAttrValueMapper pmsBaseAttrValueMapper;

    /**
     * @Description: 根据catalog3Id查询属性信息
     */
    @Override
    public List<PmsBaseAttrInfo> attrInfoList(String catalog3Id) {
        PmsBaseAttrInfo pmsBaseAttrInfo = new PmsBaseAttrInfo();
        pmsBaseAttrInfo.setCatalog3Id(catalog3Id);
        return pmsBaseAttrInfoMapper.select(pmsBaseAttrInfo);
    }

    /**
     * @param attrId
     * @Description: 根据attrId查属性值信息
     */
    @Override
    public List<PmsBaseAttrValue> getAttrValueList(String attrId) {
        PmsBaseAttrValue pmsBaseAttrValue = new PmsBaseAttrValue();
        pmsBaseAttrValue.setAttrId(attrId);
        return pmsBaseAttrValueMapper.select(pmsBaseAttrValue);
    }

    /**
     * @param pmsBaseAttrInfo
     * @Description: 保存属性和属性值两张表
     */
    @Override
    public String saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo) {
        String id = pmsBaseAttrInfo.getId();

        //如果id为空或null或“  ”，就是新添加提交过来的数据，没有主键，直接保存
        if(StringUtils.isBlank(id)){
            //先保存主表(属性信息表)，自动获取主键
            pmsBaseAttrInfoMapper.insertSelective(pmsBaseAttrInfo);

            //从主表中获取子表list集合对象
            List<PmsBaseAttrValue> pmsBaseAttrValues = pmsBaseAttrInfo.getAttrValueList();
            // 集合对象迭代保存到数据(属性值表)
            for (PmsBaseAttrValue pmsBaseAttrValue : pmsBaseAttrValues) {
                // 把主表的主键作为外键保存
                pmsBaseAttrValue.setAttrId(pmsBaseAttrInfo.getId());
                // 再保存子表
                pmsBaseAttrValueMapper.insertSelective(pmsBaseAttrValue);
            }
        } else {
            //id不为空，就是查出来的数据，用于属性值表修改：先删除全部，再插入新的

            //主表一行数据，直接update修改
            //设置修改条件
            Example example = new Example(PmsBaseAttrInfo.class);
            //正则条件设置update的依据条件
            example.createCriteria().andEqualTo("id",pmsBaseAttrInfo.getId());
            //根据example的条件来更新pmsBaseAttrInfo表的数据
            pmsBaseAttrInfoMapper.updateByExampleSelective(pmsBaseAttrInfo,example);

            //子表多行数据，先全删除，再重新插入新数据 = update
            PmsBaseAttrValue pmsBaseAttrValueDel = new PmsBaseAttrValue();
            pmsBaseAttrValueDel.setAttrId(pmsBaseAttrInfo.getId());
            //按照外键：属性表id为外键，删除属性值表所有行对应数据
            pmsBaseAttrValueMapper.delete(pmsBaseAttrValueDel);

            //删除后重新迭代插入新数据
            List<PmsBaseAttrValue> pmsBaseAttrValues = pmsBaseAttrInfo.getAttrValueList();
            for (PmsBaseAttrValue pmsBaseAttrValue : pmsBaseAttrValues) {
                pmsBaseAttrValueMapper.insertSelective(pmsBaseAttrValue);
            }
        }
        return "success";
    }


}
