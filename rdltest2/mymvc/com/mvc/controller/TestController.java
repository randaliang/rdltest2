package com.mvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@Autowired
	MyService myService;
	
	public MyService getMyService() {
		return myService;
	}


	public void setMyService(MyService myService) {
		this.myService = myService;
	}


	@RequestMapping( value="msg" )
	public String getHelloMessage() {
		return "msg";
	}
	
	
	@RequestMapping( value="path/{id}" )
	public String getPath(HttpServletRequest req, @PathVariable("id") String id  ) {
		return req.getServletPath()+id;
	}
	
	
	@RequestMapping( value="pathv/{id}/msg" )
	public String getPathv(HttpServletRequest req, @PathVariable("id") String id  ) {
		return "pathv"+id;
	}
	
	
	@RequestMapping( value="json/{id}" )
	public TestModel getJson(HttpServletRequest req, @PathVariable("id") String id  ) {
		TestModel tm = new TestModel();
		tm.setAge(11);
		tm.setName(id);
		return tm;
	}
}
