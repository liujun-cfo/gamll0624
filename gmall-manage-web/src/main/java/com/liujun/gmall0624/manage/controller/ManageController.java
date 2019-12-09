package com.liujun.gmall0624.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.liujun.gmall0624.bean.*;
import com.liujun.gmall0624.service.ManageService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin
public class ManageController {


    // 调用服务层
    @Reference
    private ManageService manageService;

    // 获取一级分类数据
    @RequestMapping("getCatalog1")
    public List<BaseCatalog1> getCatalog1(){

        return manageService.getCatalog1();
    }
    // @RequestMapping("/manage/m/{getCatalog2}/{id}")
    // @PathVariable String getCatalog2 ,@PathVariable String id
    // http://localhost:8082/getCatalog2?catalog1Id=2
    @RequestMapping("getCatalog2")
    public List<BaseCatalog2> getCatalog2 (String catalog1Id, HttpServletRequest request, BaseCatalog2 baseCatalog2){
        String catalog1Id1 = request.getParameter("catalog1Id"); // 行不？
        System.out.println("baseCatalog2:"+baseCatalog2);
        System.out.println(catalog1Id1);
        //        return manageService.getCatalog2(catalog1Id);
        return manageService .getCatalog2(baseCatalog2);
    }

    //    http://localhost:8082/getCatalog3?catalog2Id=61
    @RequestMapping("getCatalog3")
    public List<BaseCatalog3> getCatalog3(BaseCatalog3 baseCatalog3){
        return manageService.getCatalog3(baseCatalog3);
    }

    // http://localhost:8082/attrInfoList?catalog3Id=61
    @RequestMapping("attrInfoList")
    public List<BaseAttrInfo> attrInfoList(BaseAttrInfo baseAttrInfo,String catalog3Id){
        return manageService.getAttrInfoList(catalog3Id);
    }

    // http://localhost:8082/saveAttrInfo
    @RequestMapping("saveAttrInfo")
    public void saveAttrInfo(@RequestBody BaseAttrInfo baseAttrInfo){
        manageService.saveAttrInfo(baseAttrInfo);
    }
    // http://localhost:8082/getAttrValueList?attrId=86N
    @RequestMapping("getAttrValueList")
    public List<BaseAttrValue> getAttrValueList(String attrId){
        // select * from baseAttrInfo where id = attrId;
        BaseAttrInfo baseAttrInfo =  manageService.getAtrrInfo(attrId);
        return baseAttrInfo.getAttrValueList();
        // return manageService.getAttrValueList(attrId);34
    }

}
