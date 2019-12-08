package com.liujun.gmall0624.manage.service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.liujun.gmall0624.bean.*;
import com.liujun.gmall0624.manage.mapper.*;
import com.liujun.gmall0624.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ManageServiceImpl implements ManageService {

    @Autowired
    private BaseCatalog1Mapper baseCatalog1Mapper;

    @Autowired
    private BaseCatalog2Mapper baseCatalog2Mapper;

    @Autowired
    private BaseCatalog3Mapper baseCatalog3Mapper;

    @Autowired
    private BaseAttrInfoMapper baseAttrInfoMapper;

    @Autowired
    private BaseAttrValueMapper baseAttrValueMapper;

    @Autowired
    private SpuInfoMapper spuInfoMapper;

    @Autowired
    private BaseSaleAttrMapper baseSaleAttrMapper;

    @Autowired
    private SpuImageMapper spuImageMapper;

    @Autowired
    private SpuSaleAttrMapper spuSaleAttrMapper;

    @Autowired
    private SpuSaleAttrValueMapper spuSaleAttrValueMapper;


    @Override
    public List<BaseCatalog1> getCatalog1() {
        return baseCatalog1Mapper.selectAll();
    }

    @Override
    public List<BaseCatalog2> getCatalog2(String catalog1Id) {

        BaseCatalog2 baseCatalog2 = new BaseCatalog2();
        baseCatalog2.setCatalog1Id(catalog1Id);

        return baseCatalog2Mapper.select(baseCatalog2);
    }

    @Override
    public List<BaseCatalog2> getCatalog2(BaseCatalog2 baseCatalog2) {

        return baseCatalog2Mapper.select(baseCatalog2);
    }

    /**
     * 查询三级分类
     * @param baseCatalog3
     * @return
     */
    @Override
    public List<BaseCatalog3> getCatalog3(BaseCatalog3 baseCatalog3) {
        return baseCatalog3Mapper.select(baseCatalog3);
    }

    /**
     * 查询平台属性列表
     * @param baseAttrInfo
     * @return
     */
    @Override
    public List<BaseAttrInfo> getAttrInfoList(BaseAttrInfo baseAttrInfo) {
        return baseAttrInfoMapper.select(baseAttrInfo);
    }

    @Override
    @Transactional
    public void saveAttrInfo(BaseAttrInfo baseAttrInfo) {
        //修改
        if (baseAttrInfo.getId()!=null && baseAttrInfo.getId().length()>0) {
            baseAttrInfoMapper.updateByPrimaryKeySelective(baseAttrInfo);
        }else{

            //要分别插入两张表中 baseAttrInfo，baseAttrValue
            //插入baseAttrInfo中
            baseAttrInfoMapper.insertSelective(baseAttrInfo);
        }

        //baseAttrValue 修改 先删除原来的数据，再新增所有的数据
        // delete * from baseAttrValue where attrId = ? baseAttrInfo.getId()

        BaseAttrValue baseAttrValueDel = new BaseAttrValue();
        baseAttrValueDel.setAttrId(baseAttrInfo.getId());
        baseAttrValueMapper.delete(baseAttrValueDel);
        System.out.println("删除数据");


        //插入到baseAttrValue
        List<BaseAttrValue> attrValueList = baseAttrInfo.getAttrValueList();

        if (attrValueList !=null && attrValueList.size()>0) {
            for (BaseAttrValue baseAttrValue : attrValueList) {
                //保存数据 主键id要添加进去
                baseAttrValue.setAttrId(baseAttrInfo.getId());

                baseAttrValueMapper.insertSelective(baseAttrValue);
            }
        }



    }

    @Override
    public List<BaseAttrValue> getAttrValueList(String attrId) {

        BaseAttrValue baseAttrValue = new BaseAttrValue();
        baseAttrValue.setAttrId(attrId);

        return baseAttrValueMapper.select(baseAttrValue);

    }


    @Override
    public BaseAttrInfo getAttrInfo(String attrId) {
        BaseAttrInfo baseAttrInfo = baseAttrInfoMapper.selectByPrimaryKey(attrId);

        baseAttrInfo.setAttrValueList(getAttrValueList(attrId));
        return baseAttrInfo;
    }

    @Override
    public List<SpuInfo> getSpuInfoList(String catalog3Id) {
        SpuInfo spuInfo = new SpuInfo();
        spuInfo.setCatalog3Id(catalog3Id);
        return spuInfoMapper.select(spuInfo);
    }

    @Override
    public List<SpuInfo> getSpuInfoList(SpuInfo spuInfo) {
        return spuInfoMapper.select(spuInfo);
    }

    @Override
    public List<BaseSaleAttr> getBaseSaleAttrList() {
        return baseSaleAttrMapper.selectAll();
    }

    @Override
    @Transactional
    public void saveSpuInfo(SpuInfo spuInfo) {
        //spuInfo保存着所有数据
        spuInfoMapper.insertSelective(spuInfo);
        //spuImage
        List<SpuImage> spuImageList = spuInfo.getSpuImageList();
        if (checkListIsEmpty(spuImageList)) {
            for (SpuImage spuImage : spuImageList) {
                spuImage.setSpuId(spuInfo.getId());
                spuImageMapper.insertSelective(spuImage);

            }
        }

        //spuSaleAttr
        List<SpuSaleAttr> spuSaleAttrList = spuInfo.getSpuSaleAttrList();
        if (checkListIsEmpty(spuSaleAttrList)) {
            for (SpuSaleAttr spuSaleAttr : spuSaleAttrList) {
                spuSaleAttr.setSpuId(spuInfo.getId());
                spuSaleAttrMapper.insertSelective(spuSaleAttr);

                List<SpuSaleAttrValue> saleAttrValueList = spuSaleAttr.getSpuSaleAttrValueList();
                if (checkListIsEmpty(saleAttrValueList)) {
                    for (SpuSaleAttrValue spuSaleAttrValue : saleAttrValueList) {
                        spuSaleAttrValue.setSpuId(spuInfo.getId());
                        spuSaleAttrValueMapper.insertSelective(spuSaleAttrValue);
                    }
                }
            }
        }

    }

    public <T> boolean checkListIsEmpty(List<T> list){
        if (list!=null && list.size()>0){
            return true;
        }
        return false;
    }
}
