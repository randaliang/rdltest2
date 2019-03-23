package com.priv.gabriel.service;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.priv.gabriel.entity.User;
import com.priv.gabriel.repository.UserRepository;

@Service
public class TestService implements InitializingBean {
	@Autowired
	UserRepository udao;

	@Override
	public void afterPropertiesSet() throws Exception {
		
		List<User> list = udao.all();
		System.out.println(list.toString());
	}
	
}
