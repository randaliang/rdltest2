package test;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Iterator {

	public static void main( String args[] ) {
		Map<String,String> map = new HashMap<>();
		for( String s : iterableOf(map.keySet().stream()) ) {
			
		}
		
		for( String s : map.keySet().stream().iterator() ) {
			
		}
		
	while(  map.keySet().stream().iterator().hasNext() ) {
			
	}
		
	}
	
	public static<E> Iterable<E> iterableOf( Stream<E> stream ){
//		return (Iterable<E>) stream.iterator();
		return stream::iterator;
		
	}
	
	public static <E> Stream<E> streamOf(Iterable<E> iterable) {
		return StreamSupport.stream(iterable.spliterator(), false);
		}
	
}
