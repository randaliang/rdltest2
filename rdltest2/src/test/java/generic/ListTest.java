package generic;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

public class ListTest {

	@Test
	public void test() {
		List list = new ArrayList<String>();
		list.add("1");
		list.add(2);
		list.add(new BigDecimal("3"));
		qmTest(list);
		
		List<BigDecimal> blist = new ArrayList<BigDecimal>();
		qmExtendsTest(blist);
	}
	
	public void qmTest(List<?> list) {
		for( Object m : list ) {
			System.out.println( m.getClass() + ":" + m );
		}
	}
	
	
	public void qmObjectTest(List<Object> list) {
		list.add("111");
		list.add(1234);
		
		for( Object m : list ) {
			System.out.println( m.getClass() + ":" + m );
		}
	}
	
	
	
	/**
	 * exends只能遍历
	 * @param list
	 */
	public void qmExtendsTest(List<? extends Number> list) {
		for( Number m : list ) {
			System.out.println( m.getClass() + ":" + m );
		}
		
	}

	
	public void qmSuperTest(List<? super BigDecimal> list) {
		list.add(new Integer("11"), null);
		for( Object o : list ) {
			System.out.println( o.getClass() + ":" + o );
		}
		list.add(new BigDecimal("1"));
		list.get(0);
	}


}
