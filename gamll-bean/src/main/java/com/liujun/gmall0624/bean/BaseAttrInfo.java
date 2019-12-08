package com.liujun.gmall0624.bean;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
public class BaseAttrInfo implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)//表示主键自增
    private String id;
    @Column
    private String attrName;
    @Column
    private String catalog3Id;

    //此字段不是数据库中的添加一个注解
    @Transient
    private List<BaseAttrValue> attrValueList;

}
