package com.superhope;


import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.beetl.sql.core.ClasspathLoader;
import org.beetl.sql.core.ConnectionSource;
import org.beetl.sql.core.ConnectionSourceHelper;
import org.beetl.sql.core.Interceptor;
import org.beetl.sql.core.SQLLoader;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.SQLReady;
import org.beetl.sql.core.UnderlinedNameConversion;
import org.beetl.sql.core.db.DBStyle;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.query.Query;
import org.beetl.sql.ext.DebugInterceptor;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import com.superhope.dao.UserDao;
import com.superhope.dao.pojo.User;
import com.superhope.dao.pojo.UserExtend;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { Application.class })
public class Demos {

	@Autowired
	private SQLManager sqlManager;
	
	@Autowired
	private UserDao userDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
//		log.info("setUpBeforeClass");
//		
//		String driver = "com.mysql.cj.jdbc.Driver";
//		String url = "jdbc:mysql://192.168.65.166:3306/rdl_test0?useUnicode=true&characterEncoding=utf8&useSSL=false";
//		String userName = "root";
//		String password = "123456";
//				
//		ConnectionSource source = ConnectionSourceHelper.getSimple(driver, url, userName, password);
//		
//		DBStyle mysql = new MySqlStyle();
//		SQLLoader loader = new ClasspathLoader("/sql");
////		sqlManager = new SQLManager(mysql,loader,source,new DefaultNameConversion(), new Interceptor[]{new DebugInterceptor()});
//		sqlManager = new SQLManager(mysql,loader,source,new UnderlinedNameConversion(), new Interceptor[]{new DebugInterceptor()});
	}
	
	@Test
	public void insert(){
		User user = new User();
		user.setAge(19);
		user.setName("xiandafu");
		sqlManager.insert(user);
		
	}
	
	@Test
	public void testQuery(){
		int id = 1;
		User user = sqlManager.unique(User.class,id);
		log.info("testquery------" + user.toString());
	}
	
	
	
	@Test
	public void tesExetendtQuery(){
		int id = 1;
		UserExtend user = sqlManager.unique(UserExtend.class,id);
		log.info("testquery------" + user.toString());
	}
	
	
	@Test
	public void updateTemplateById(){
		User newUser = new User();
		newUser.setId(1);
		newUser.setAge(20);
		newUser.setName("name----------");
		sqlManager.updateTemplateById(newUser);
	}
	
	@Test
	public void queryTemplate(){
		//模板查询
		User query = new User();
		query.setName("xiandafu");
		List<User> list = sqlManager.template(query);
		log.info("list.size@@@@@@@@@@@" + list.size() );
	}
	
	@Test
	public void lambdaQuery(){
		//Query查询
		List<User> userList = sqlManager.lambdaQuery(User.class).andEq(User::getName,"xiandafu").select();
		log.info("list.size====================" + userList.size() );
	}
	
	@Test
	public void singleTest(){
		User user = sqlManager.unique(User.class, 1);
		log.info("1111111111-----------" + user );
	}

	
	@Test
	public void allTest(){
		List<User> userList = sqlManager.all(User.class, 5, 100);
		log.info("userList-----------" + userList.size() );
	}

	
	@Test
	public void queryTest(){
		List<User> list = sqlManager.lambdaQuery(User.class).andEq(User::getName, "hi").orderBy(User::getCreateDate).select();
		log.info( "queryTest" + list.size() );
	}

	
	@Test
	public void templateQueryTest(){
		User user = new User();
		user.setAge(19);
		user.setName("xiandafu");
		
		List<User> list = sqlManager.template(user);
		log.info( "templateQueryTest" + list.size() );
	}

	
	
	@Test
	public void templateMinMaxQueryTest(){
		User user = new User();
		user.setAge(19);
		user.setName("xiandafu");
		user.setMaxDate(new Date());
		user.setMinDate(new Date());
		List<User> list = sqlManager.template(user);
		log.info( "templateQueryTest" + list.size() );
	}

	@Test
	public void sqlIdTest(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("name","xiandafu");
		List<User> list = sqlManager.select("user.querybyname", User.class,map);
		log.info( "templateQueryTest" + list.size() );
	}
	
	@Test
	public void sqlIdPageTest(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("name","xiandafu");
		List<User> list = sqlManager.select("user.querybyname", User.class,map,1,10);
		log.info( "templateQueryTest" + list.size() );
	}
	
	
	@Test
	public void sqlIdSimplePageTest(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("name","xiandafu%");
		PageQuery<User> pq = new PageQuery<User>();
		pq.setPageNumber(2);
		pq.setPageSize(3);
//		pq.setOrderBy("id");
		pq.setPara("name", "xiandafu%");
		
		PageQuery<User> p2 = sqlManager.pageQuery("user.queryNewUser", User.class,pq);
		log.info( "templateQueryTest" + p2);
	}
	
	@Test
	public void queryNewUserest(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("name","xiandafu%");
		PageQuery<User> pq = new PageQuery<User>();
		pq.setPageNumber(2);
		pq.setPageSize(3);
//		pq.setOrderBy("id");
		pq.setPara("name", "xiandafu%");
		
		PageQuery<User> p2 = sqlManager.pageQuery("user.queryNewUser", User.class,pq);
		log.info( "templateQueryTest" + p2);
	}
	
	
	
	@Test
	public void sqlId1SimplePageTest(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("name","xiandafu%");
		PageQuery<User> pq = new PageQuery<User>();
		pq.setPageNumber(2);
		pq.setPageSize(3);
//		pq.setOrderBy("id");
		pq.setPara("name", "xiandafu%");
		pq.setOrderBy("id");
		PageQuery<User> p2 = sqlManager.pageQuery("user.query1SqlNewUser", User.class,pq);
		log.info( "templateQueryTest" + p2);
	}
	
	@Test
	public void sqlId2SimplePageTest(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("name","xiandafu%");
		PageQuery<User> pq = new PageQuery<User>();
		pq.setPageNumber(2);
		pq.setPageSize(3);
//		pq.setOrderBy("id");
		pq.setPara("name", "xiandafu%");
//		pq.setOrderBy("id");
		PageQuery<User> p2 = sqlManager.pageQuery("user.query2SqlNewUser", User.class,pq);
		log.info( "templateQueryTest" + p2);
	}

	
	//-------------直接执行普通sql---------
	@Test
	public void executeCommonSelect(){
		List<User> list = sqlManager.execute(new SQLReady("select * from user where name=? and age > ?","xiandafu",0),User.class);
		log.info(list.toString());
	}
	

	
	@Test
	public void testLock(){
		sqlManager.lock(User.class, 1);
		log.info("lock execute");
	}
	

	@Test
	public void testUserDao(){
		
		PageQuery<User> pq = new PageQuery<User>();
		pq.setPageNumber(2);
		pq.setPageSize(3);
//		pq.setOrderBy("id");
		pq.setPara("name", "xiandafu%");
		PageQuery<User> pq1  = userDao.queryNewUser(pq);
		pq1.toString();
	}
	
	@Test
	public void queryHTest(){
		log.info("queryHTest-------" );
	}
	
	
	@Test
	public void queryH2Test() {
		Query<User> query = userDao.createQuery();
		PageQuery pq = query.or(query.condition().andLike("name", "%t%")).andIn("id", Arrays.asList(1637, 1639, 1640))
				.or(query.condition().andEq("id", 1640)).page(1, 1);
		log.info("queryHTest-------" + pq.getList().size());
	}
	
	
	@Test
	public void queryOrmTest() {
		Object o = userDao.single(1).get("roleid");
//		log.info(o.toString());
	}
	
	
	@Test
	public void querybynamepercent() {
		Map map = new HashMap();
		map.put("name", "xiandafu");
		Object o = userDao.querybynamepercent( "xiandafu");
		log.info(o.toString());
	}
	
	
	@Test
	public void queryIdIn() {
		Object o = userDao.queryIdIn(new Integer[]{1,2,3});
		log.info(o.toString());
	}
	
	
	public void insertT(){
//		sqlManager.insert(paras)
//		log.info( "templateQueryTest" + p2);
	}

	
	

	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		log.info("tearDownAfterClass");
	}
	
	@Before
	public void before(){
		log.info("before");
	}

	@After
	public void after(){
		log.info("after");
	}
	


}
