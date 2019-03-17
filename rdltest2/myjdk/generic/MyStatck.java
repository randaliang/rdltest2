package generic;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Stack;

public class MyStatck<E> extends Stack<E> {

	public void popAll(Collection<? super E> dst) {
		while (!isEmpty())
			dst.add(pop());
	}

	public static void main( String args[] ) {
		MyStatck<BigDecimal> m = null;
		Collection<Number> c =  null;
		m.popAll(c);
	}
	
	
}
