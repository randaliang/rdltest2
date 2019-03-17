package test;

import static org.junit.Assert.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.groupingBy;

import org.junit.Before;
import org.junit.Test;

public class LamdaTest {

	List<String> list = new ArrayList<>();
	
	@Before
	public void setup() {
		list.add("stream");
		list.add("lamdata");
		list.add("Before");
		list.add("test");
		list.add("match");
		list.add("abort");
		list.add("apollo");
		list.add("test");
		list.add("match");
		list.add("find");
		list.add("filter");
		list.add("itmes");
		list.add("aa");
		
	}
	
	@Test
	public void testFilter() {
		//普通的lamda
		list.sort( (x,y)-> x.length() - y.length()  );
		
		list.sort((x,y)-> Integer.compare(x.length(), y.length()) );
		
		//方法引用
		list.sort( comparingInt(String :: length ) );
	}

	
	
	@Test
	public void testFuctionCompose() {
		Function<Integer,Integer> a = x-> x+5 ;
		Function<Integer,Integer> b = x-> x*5 ;
		
		int m = a.compose(b ).apply(5);
		assertEquals(30, m);
		
		
	}

	@Test
	public void testFuctionAndthen() {
		Function<Integer,Integer> a = x-> x+5 ;
		Function<Integer,Integer> b = x-> x*5 ;
		int m = a.andThen(b ).apply(5);
		assertEquals(50, m);
	}

	@Test
	public void mapMerge() {
		Map<String, Integer> map = new HashMap<>();
		map.merge("1", 1, (x, y) -> x + y);
		map.merge("1", 1, (x, y) -> x + y);
		assertEquals(map.get("1"), new Integer(2));
		map.merge("1", 1, Integer::sum);
		assertEquals(map.get("1"), new Integer(3));
	}
	
	@Test
	public void append() {
		StringBuffer sb = new StringBuffer();
		sb.append("string");
		sb.append("ok");
	}
	
	@Test
	public void testcolllect() {
		list.stream()
				.collect(groupingBy(word -> word.chars().sorted()
						.collect(StringBuilder::new, (sb, c) -> sb.append((char) c), StringBuilder::append).toString()))
				.values().stream().filter(group -> group.size() >= 1).map(group -> group.size() + ": " + group)
				.forEach(System.out::println);
	}
	
}
