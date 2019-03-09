package com.superhope;

import java.util.List;

import org.springframework.aop.framework.AopContext;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.priv.gabriel.repository.UserRepository;
import com.superhope.dao.UserDao;

@Service 

public class TestService implements InitializingBean,ApplicationContextAware{
	@Autowired
	JdbcTemplate jd;
	
	@Autowired
	UserDao dao;
	
	private ApplicationContext applicationContext = null;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	
	
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void mulTransTest(){
		
		jd.execute("delete from b_table");
		jd.execute("INSERT INTO b_table(a,b) VALUES(11,5)");
		
	
		try{
//			((TestService)AopContext.currentProxy()).insertSingle();
			TestService t = applicationContext.getBean(TestService.class);
			t.insertSingle();
		}catch(Exception e){
			e.printStackTrace();
		}
		

		
	
	}
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void insertSingle(){
		jd.execute("delete from t_config");
		jd.execute("INSERT INTO t_config(id,STATUS) VALUES(11,'5')");
		if(true){
			throw new RuntimeException("222");
		}
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
//		List list = jd.queryForList("SELECT * FROM b_table");
//		System.out.println( list.size() );
		dao.all();
	}

	
}
