package com.liujun.gmall0624.service;

import com.liujun.gmall0624.bean.*;

import java.util.List;

public interface ManageService {

    //获取一级分类
    List<BaseCatalog1> getCatalog1();

    //获取二级分类
    List<BaseCatalog2> getCatalog2(String catalog1Id);

    //根据条件查询
    List<BaseCatalog2> getCatalog2(BaseCatalog2 baseCatalog2);

    //查询三级分类
    List<BaseCatalog3> getCatalog3(BaseCatalog3 baseCatalog3);

    //查询所有平台属性列表
    List<BaseAttrInfo> getAttrInfoList(BaseAttrInfo baseAttrInfo);

    //保存平台属性值
    void saveAttrInfo(BaseAttrInfo baseAttrInfo);

    //根据attrId获取attrValueList集合
    List<BaseAttrValue> getAttrValueList(String attrId);

    BaseAttrInfo getAttrInfo(String attrId);

    List<SpuInfo> getSpuInfoList(String catalog3Id);

    List<SpuInfo> getSpuInfoList(SpuInfo spuInfo);

    // 查询基本销售属性表
    List<BaseSaleAttr> getBaseSaleAttrList();


    //保存销售属性
    void saveSpuInfo(SpuInfo spuInfo);
}
