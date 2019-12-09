package com.liujun.gmall0624.manage.mapper;

import com.liujun.gmall0624.bean.SpuSaleAttr;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SpuSaleAttrMapper extends Mapper<SpuSaleAttr> {
    //根据spuId查询销售属性
    List<SpuSaleAttr> selectSpuSaleAttrList(String spuId);
}
