package com.superhope;
import javax.annotation.security.RunAs;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes ={ Application.class })
public class ProxyTest {
	@Autowired
	TestService ts;
	
	
	@Test
	public void mulProxy(){
		ts.mulTransTest();
	}
	
	@Test
	public void a(){
		System.out.println("xxx");
	}
}
