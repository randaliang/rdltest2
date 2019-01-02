package com.superhope;

import java.util.List;

import org.beetl.sql.core.ClasspathLoader;
import org.beetl.sql.core.ConnectionSource;
import org.beetl.sql.core.ConnectionSourceHelper;
import org.beetl.sql.core.DefaultNameConversion;
import org.beetl.sql.core.Interceptor;
import org.beetl.sql.core.SQLLoader;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.UnderlinedNameConversion;
import org.beetl.sql.core.db.DBStyle;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.core.query.Query;
import org.beetl.sql.ext.DebugInterceptor;
import org.beetl.sql.ext.gen.GenConfig;

import com.superhope.dao.pojo.User;

public class BeetlCreateMdTest {

	
	public static void main( String args[] ){
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://192.168.65.166:3306/rdl_test0?useUnicode=true&characterEncoding=utf8&useSSL=false";
		String userName = "root";
		String password = "123456";
				
		ConnectionSource source = ConnectionSourceHelper.getSimple(driver, url, userName, password);
		
		DBStyle mysql = new MySqlStyle();
		SQLLoader loader = new ClasspathLoader("/sql");
		SQLManager sqlManager = new SQLManager(mysql,loader,source,new DefaultNameConversion(), new Interceptor[]{new DebugInterceptor()});
		//sql.genPojoCodeToConsole("userRole"); 快速生成，显示到控制台
		// 或者直接生成java文件
		GenConfig config = new GenConfig();
		config.preferBigDecimal(true);
//		config.setBaseClass("com.test.User");
		try {
//			sqlManager.genPojoCode("UserRole","com.test",config);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			sqlManager.genPojoCodeToConsole("user");
			sqlManager.genSQLTemplateToConsole("user");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	    

	}
	
//	public static void a( String args[] ){
//		
//		DBStyle mysql = new MySqlStyle();
//		// sql语句放在classpagth的/sql 目录下
//		SQLLoader loader = new ClasspathLoader("/sql");
//		// 数据库命名跟java命名一样，所以采用DefaultNameConversion，还有一个是UnderlinedNameConversion，下划线风格的，
//		UnderlinedNameConversion nc = new  UnderlinedNameConversion();
//		// 最后，创建一个SQLManager,DebugInterceptor 不是必须的，但可以通过它查看sql执行情况
//		SQLManager sqlManager = new SQLManager(mysql,loader,source,nc,new Interceptor[]{new DebugInterceptor()});
//
//
//		//使用内置的生成的sql 新增用户，如果需要获取主键，可以传入KeyHolder
//		User user = new User();
//		user.setAge(19);
//		user.setName("xiandafu");
//		sqlManager.insert(user);
//
//		//使用内置sql查询用户
//		int id = 1;
//		user = sqlManager.unique(User.class,id);
//
//		//模板更新,仅仅根据id更新值不为null的列
//		User newUser = new User();
//		newUser.setId(1);
//		newUser.setAge(20);
//		sqlManager.updateTemplateById(newUser);
//
//		//模板查询
//		User query = new User();
//		query.setName("xiandafu");
//		List<User> list = sqlManager.template(query);
//
//		//Query查询
////		Query userQuery = sqlManager.query(User.class);
////		List<User> users = userQuery.lambda().andEq(User::getName,"xiandafy").select();
////
////		//使用user.md 文件里的select语句，参考下一节。
////		User query2 = new User();
////		query.setName("xiandafu");
////		List<User> list2 = sqlManager.select("user.select",User.class,query2);
////
////		// 这一部分需要参考mapper一章
////		UserDao dao = sqlManager.getMapper(UserDao.class);
////		List<User> list3 = dao.select(query2);
//	}
}
