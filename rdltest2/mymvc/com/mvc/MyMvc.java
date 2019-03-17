package com.mvc;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import aspect.SampleAopApplication;

@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})

public class MyMvc {

	public static void main(String ... args) {
		System.out.println("===========");
		SpringApplication.run(MyMvc.class, args);
	}
	
	public void test() {
		DispatcherServlet d = null;
		RequestMappingHandlerMapping rm = null;
		RequestMappingHandlerAdapter a = null;
		AutowiredAnnotationBeanPostProcessor aa = null;
		aa.postProcessAfterInitialization(null, null);
		aa.postProcessBeforeInitialization(null, null);
		ConfigurableListableBeanFactory c = null;
		DefaultListableBeanFactory dl = null;
//		dl.getBean(name)
		AbstractAutowireCapableBeanFactory au = null;
//		au.
		
		DefaultListableBeanFactory df = null;
//		df.registerAlias(beanName, alias);
		
		AnnotationConfigServletWebServerApplicationContext con = null;
		
//		c.getBeanDefinition(beanName)
		try {
//			rm.getHandler(request)
			d.init();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			
		}
	}
}
