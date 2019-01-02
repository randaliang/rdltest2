package com.test;
import java.math.*;
import java.util.Date;
import java.sql.Timestamp;
import org.beetl.sql.core.annotatoin.Table;


/* 
* 
* gen by beetlsql 2018-12-31
*/
@Table(name="rdl_test0.UserRole")
public class userrole   {
	
	private Integer id ;
	private String username ;
	
	public userrole() {
	}
	
	public Integer getId(){
		return  id;
	}
	public void setId(Integer id ){
		this.id = id;
	}
	
	public String getUsername(){
		return  username;
	}
	public void setUsername(String username ){
		this.username = username;
	}
	

}
