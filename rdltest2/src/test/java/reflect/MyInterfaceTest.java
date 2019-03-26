package reflect;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.assertj.core.util.Arrays;
import org.junit.Test;
import org.mockito.internal.util.collections.ListUtil;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;

public class MyInterfaceTest {

	@Test
	public void test() {
		
		List list = getAllInterClasses(AnnotationConfigServletWebServerApplicationContext.class);
		List list2 = getAllInterClasses(DefaultListableBeanFactory.class);

		Collection col = CollectionUtils.intersection(list, list2);
		Set set = new HashSet();
		set.addAll(col);
		
		for( Object o : set ) {
			System.out.println(o);
		}
	}

	private List getAllInterClasses( Class c ){
		if( c == null ||  c.getName().equals("java.lang.Object") ) {
			return null;
		}
	
		List list = new ArrayList();
		Class<?>[] arr = c.getInterfaces();
		
		if( arr != null && arr.length > 0 ) {
			list.addAll( Arrays.asList(arr));
			for( Class o : arr ) {
				list.addAll(getAllInterClasses( o ));
			}
		}
		
		List tempList = getAllInterClasses (c.getSuperclass());
		if( !CollectionUtils.isEmpty(tempList) ) {
			list.addAll(tempList);
		}
		return list;
	}
	
}
