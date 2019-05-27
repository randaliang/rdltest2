package com.mvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

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
}
