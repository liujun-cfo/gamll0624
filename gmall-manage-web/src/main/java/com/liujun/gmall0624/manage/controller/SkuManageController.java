package com.liujun.gmall0624.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.liujun.gmall0624.bean.BaseAttrInfo;
import com.liujun.gmall0624.bean.SkuInfo;
import com.liujun.gmall0624.bean.SpuImage;
import com.liujun.gmall0624.bean.SpuSaleAttr;
import com.liujun.gmall0624.service.ManageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class SkuManageController {

    @Reference
    private ManageService manageService;

    @RequestMapping("spuImageList")
    public List<SpuImage> spuImageList(SpuImage spuImage){
        return manageService.getSpuImageList(spuImage);
    }

    @RequestMapping("spuSaleAttrList")
    public List<SpuSaleAttr> getSpuSaleAttrList(String spuId){
        return manageService.getSpuSaleAttrList(spuId);
    }

    @RequestMapping("saveSkuInfo")
    public void saveSkuInfo(@RequestBody SkuInfo skuInfo){
        manageService.saveSkuInfo(skuInfo);
    }
}
