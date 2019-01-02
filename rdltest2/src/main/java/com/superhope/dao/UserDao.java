package com.superhope.dao;

import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;

import com.superhope.dao.pojo.User;
@SqlResource("user")
public interface UserDao extends BaseMapper<User>  {

	PageQuery<User> queryNewUser(PageQuery<User> pq);
}
