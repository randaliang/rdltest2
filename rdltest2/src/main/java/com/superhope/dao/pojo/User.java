package com.superhope.dao.pojo;


import java.math.*;
import java.util.Date;

import org.beetl.sql.core.annotatoin.DateTemplate;
import org.beetl.sql.core.annotatoin.TableTemplate;

import lombok.Data;
/*
*
* gen by beetlsql 2016-01-06
*/
@Data
@TableTemplate("order by id desc ")
public class User  {
    private Integer id ;
    private Integer age ;
    //用户角色
    private Integer roleId ;
    private String name ;
    //用户名称
    private String userName ;
    
    @DateTemplate(accept="minDate,maxDate",compare=">=,<")
    private Date createDate ;
    
    private Date minDate;
    
    private Date maxDate;
    
 

}