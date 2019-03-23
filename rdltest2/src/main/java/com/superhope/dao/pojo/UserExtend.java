package com.superhope.dao.pojo;

import org.beetl.sql.core.annotatoin.Table;

import lombok.Data;

@Data
@Table(name = "user")
public class UserExtend extends User{

	private Integer extendId;
}
