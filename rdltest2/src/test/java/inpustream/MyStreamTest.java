package inpustream;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

import org.junit.Test;
import org.springframework.web.util.CookieGenerator;

public class MyStreamTest {

	@Test
	public void test() {
		String s =MyStreamTest.class.getClassLoader().getResource("application.properties").getPath();
		System.out.println(s);
	}

	
	@Test
	public void testPro() {
		String s =CookieGenerator.class.getClassLoader().getResource("/spring-web.kotlin_module").getPath();
		System.out.println(s);
	}

	@Test
	public void testSpringLoad() {
		String FACTORIES_RESOURCE_LOCATION = "META-INF/spring-web.kotlin_module";
		ClassLoader classLoader = MyStreamTest.class.getClassLoader();
		try {
			Enumeration<URL> urls = (classLoader != null ?
					classLoader.getResources(FACTORIES_RESOURCE_LOCATION) :
					ClassLoader.getSystemResources(FACTORIES_RESOURCE_LOCATION));
			while (urls.hasMoreElements()) {
				System.out.println( urls.nextElement() );
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	 spring-web
}
