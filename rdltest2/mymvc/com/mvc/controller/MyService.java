package com.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class MyService {

	@Autowired
	private JdbcTemplate jd;
	
}
