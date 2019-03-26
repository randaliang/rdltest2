package generic;

import java.lang.annotation.Annotation;

public class Parent {

	public static void main( String args[] ) {
		try {
			Class clazz = Class.forName("org.springframework.stereotype.Service");
			Annotation[] a = clazz.getAnnotations();
			for( Annotation m : a  ) {
				System.out.println(m + "==" + m.getClass());
				
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}