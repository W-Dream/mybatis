package com.sinosoft.mybatis.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description:
 * @author: wanghuangpeng
 * @time: 2019/11/15 13:49
 */
@Data
public class Student implements Serializable {
    private int id;
    private String name;
    private Date birthday;
}
