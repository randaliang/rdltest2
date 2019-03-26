package com.superhope.dao;

import java.util.List;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;

import com.superhope.dao.pojo.Role;
import com.superhope.dao.pojo.User;
@SqlResource("user")
public interface UserDao extends BaseMapper<User>  {
	PageQuery<User> queryNewUser(PageQuery<User> pq);
	
	List<User> querybynamepercent( @Param(value = "name") String name);
	
	List<User> queryIdIn( @Param(value = "ids") Integer[] ids);
	
	List<Role> queryAllRole();
}
