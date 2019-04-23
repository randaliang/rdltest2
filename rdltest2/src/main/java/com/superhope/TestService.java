package com.superhope;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
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

public class TestService implements InitializingBean, ApplicationContextAware {
	@Autowired
	JdbcTemplate jd;

	UserDao dao;

	private ApplicationContext applicationContext = null;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void mulTransTest() {

		jd.execute("delete from b_table");
		jd.execute("INSERT INTO b_table(a,b) VALUES(11,5)");

		try {
//			((TestService)AopContext.currentProxy()).insertSingle();
			TestService t = applicationContext.getBean(TestService.class);
			t.insertSingle();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void insertSingle() {
		jd.execute("delete from t_config");
		jd.execute("INSERT INTO t_config(id,STATUS) VALUES(11,'5')");
		if (true) {
			throw new RuntimeException("222");
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
//		List list = jd.queryForList("SELECT * FROM b_table");
//		System.out.println( list.size() );
//		dao.all();
//		insertTable( new File("d:\\temp\\20190423\\pb_plan_agent_note.sql") );
		insertTable(new File("d:\\temp\\20190423\\pb_pay_voucher.sql"));

	}

	public void insertTable(File file) {
		String s = null;
		try {

			System.out.println("begin----" + file.getName());

			InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "GBK"); // 或GB2312,GB18030
			BufferedReader read = new BufferedReader(isr);
			StringBuilder b = new StringBuilder();
			int i = 0;
			while (true) {
				s = read.readLine();
				if (s == null) {
					break;
				}
				b.append(s);
				if (s.contains(";")) {
					String content = b.toString();
					int end = content.indexOf(";");
					if (end == -1) {
						break;
					}
					s = content.substring(0, end);
//					System.out.println(s);
					try {
						jd.execute(s);
						i++;
						if(i%100 == 0) {
							System.out.println(i);
						}
					}catch( Exception e ) {
						System.out.println(e.getMessage());
					}
				
					b.setLength(0);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("end----" + file.getName());
	}

	public static byte[] convertFileToBytes(File file) {
		FileInputStream inputStream = null;
		ByteArrayOutputStream out = null;
		try {
			inputStream = new FileInputStream(file);
			out = new ByteArrayOutputStream();

			byte b[] = new byte[1024];
			int length = 0;
			while ((length = inputStream.read(b)) != -1) {
				out.write(b, 0, length);
			}

			return out.toByteArray();

		} catch (FileNotFoundException e) {
			return null;
		} catch (IOException e) {
			return null;
		} finally {
			if (out != null) {
				try {
					out.close();
					out = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (inputStream != null) {
				try {
					inputStream.close();
					inputStream = null;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

}
