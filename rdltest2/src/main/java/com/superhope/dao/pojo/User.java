package com.superhope.dao.pojo;


import java.math.*;
import java.util.Date;

import org.beetl.sql.core.TailBean;
import org.beetl.sql.core.annotatoin.DateTemplate;
import org.beetl.sql.core.annotatoin.TableTemplate;
import org.beetl.sql.core.orm.OrmCondition;
import org.beetl.sql.core.orm.OrmQuery;

import lombok.Data;
/*
*
* gen by beetlsql 2016-01-06
*/
@Data
@TableTemplate("order by id desc ")
@OrmQuery(
	    value={
	        @OrmCondition(target=Role.class,attr="roleId",targetAttr="id" ,sqlId="user.selectRole",type=OrmQuery.Type.MANY)
	    }
	)
public class User extends TailBean {
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