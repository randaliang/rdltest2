package test;

import static org.junit.Assert.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.counting;

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
	
//	@Test
//	public void test() {
//		//普通的lamda
//		list.sort( (x,y)-> x.length() - y.length()  );
//		
//		list.sort((x,y)-> Integer.compare(x.length(), y.length()) );
//		
//		//方法引用
//		list.sort( comparingInt(String :: length ) );
//	}
//	
//
//	
//	
//	@Test
//	public void testFuctionCompose() {
//		Function<Integer,Integer> a = x-> x+5 ;
//		Function<Integer,Integer> b = x-> x*5 ;
//		
//		int m = a.compose(b ).apply(5);
//		assertEquals(30, m);
//		
//		
//	}
//
//	@Test
//	public void testFuctionAndthen() {
//		Function<Integer,Integer> a = x-> x+5 ;
//		Function<Integer,Integer> b = x-> x*5 ;
//		int m = a.andThen(b ).apply(5);
//		assertEquals(50, m);
//	}
//
//	@Test
//	public void mapMerge() {
//		Map<String, Integer> map = new HashMap<>();
//		map.merge("1", 1, (x, y) -> x + y);
//		map.merge("1", 1, (x, y) -> x + y);
//		assertEquals(map.get("1"), new Integer(2));
//		map.merge("1", 1, Integer::sum);
//		assertEquals(map.get("1"), new Integer(3));
//	}
//	
//	@Test
//	public void append() {
//		StringBuffer sb = new StringBuffer();
//		sb.append("string");
//		sb.append("ok");
//	}
//	
	@Test
	public void testcolllect() {
		list.stream()
				.collect(groupingBy(word -> word.chars().sorted()
						.collect(StringBuilder::new, (sb, c) -> sb.append((char) c), StringBuilder::append).toString()))
				.values().stream().filter(group -> group.size() >= 1).map(group -> group.size() + ": " + group)
				.forEach(System.out::println);
	}
	
	@Test
		
	public void testcollectCount() {
		Map map = list.stream().collect(groupingBy(String::length, counting()));
		map.toString();
	}
//	
//	@Test
//	public void testFilter() {
//		list.stream().filter(x->x.startsWith("a"))
//		.forEach(x->System.out.println("filter==" +x) );
//	}
//	
	@Test
	public void testMap() {
		list.stream().map(x -> x.toUpperCase())
			.forEach(x->System.out.println("map==" +x) );
		
		list.stream().map(String::length)
		.forEach(x->System.out.println("intmap==" +x) );
	}
	
	@Test
	public void testMax() {
		Optional<String> r = list.stream().max((x,y)->x.compareTo(y));
		if(r.isPresent()) {
			System.out.println("testMax" + r.get() );
		}
		
		list.stream().collect( Collectors.maxBy((x,y)->x.compareTo(y)) );
	}
	 
//	@Test
//	public void testDistinct() {
//		long dis = list.stream().distinct().count();
//		long ori = list.stream().count();
//		assertTrue("distinct 测试错误", ori > dis);
//	}
//	
//	@Test
//	public void testSort() {
//		list.stream().sorted( String.CASE_INSENSITIVE_ORDER )
//			.peek(x->System.out.println("peek==" +x) )
//			.sorted()
//			.forEach(x->System.out.println("sort1==" +x) );
//	}
//	
//	@Test
//	public void testArray() {
//		Object[] array = list.stream().sorted( String.CASE_INSENSITIVE_ORDER )
//			.toArray();
//		System.out.println( array.length );
//		
//		String[] a1 = list.stream().sorted( String.CASE_INSENSITIVE_ORDER )
//				.toArray(String[]::new);
//			System.out.println( a1.length );
//	}
//		
	
	@Test
	public void testReduce() {
		Map<String,String> map = new HashMap<>();
		StringBuilder sb = list.stream()//.sorted( String.CASE_INSENSITIVE_ORDER )
			.reduce(new StringBuilder(), StringBuilder::append ,  StringBuilder::append);
		System.out.println( sb.toString());
		
		list.stream().sorted( String.CASE_INSENSITIVE_ORDER )
		.reduce(new StringBuilder(), (x,y)->{ return x.append(y); } ,  StringBuilder::append);
		System.out.println( sb.toString());
	
	
		//模拟group by
//		list.stream().sorted( String.CASE_INSENSITIVE_ORDER )
//				.reduce(new HashMap<String,List<String>>(),
//						(x,y)->{ x.put(y,new ArrayList<String>()); return x;  } ,null);
		
//		Map map = list.stream().sorted( String.CASE_INSENSITIVE_ORDER )
//		.reduce(new HashMap<String,List<String>>(),
//				(x,y)->{ 
//						 x.computeIfAbsent(y, (unused) -> new ArrayList<>());
//				return x;  } ,
//				(x,y)->{   return x; }
//				);
//		
//		
//		map.toString();

	}

//	@Test
//	public void testMin() {
//		Optional<String> s = list.stream().min( (x,y)->x.compareTo(y)  );
//		s.isPresent();
//		
//	}
	@Test
	public void testToMap() {
		Map<String,String> map =list.stream().distinct()
				.collect(Collectors.toMap( String::toString, String::toString)  );
		map.toString();
		
//		   List<String> collect = alpha.stream().map(String::toUpperCase).collect(Collectors.toList());
//	        System.out.println(collect); //[A, B, C, D]
	 
	        // Extra, streams apply to any data type.
	        List<Integer> num = Arrays.asList(1,2,3,4,5);
	        List<Integer> collect1 = num.stream().map(n -> n * 2).collect(Collectors.toList());
	        collect1.size();
	}
	
}


	
	
//	List<String> numList = Arrays.asList("1", "2", "3", "4", "5", "6");
//	 numList.stream()
//			.reduce(new StringBuilder(), 
//					(a, b) -> {
//						a.append(b);
//						return a;},
//	 (a, b) -> null);
//	System.out.println(result);
	
	
//	List<Integer> numList = Arrays.asList(1, 2, 3, 4, 5, 6);
//	ArrayList<String> result = numList.stream()
//			.reduce(new ArrayList<String>(), 
//					(a, b) -> {
//						a.add("element-" + Integer.toString(b));
//						return a;},
//	 (a, b) -> null);
//	System.out.println(result);
	
	
//	List<Integer> numList = Arrays.asList(1, 2, 3, 4, 5, 6);
//	ArrayList<String> result = numList.stream()
//			.reduce(new ArrayList<String>(), 
//					(a, b) -> {
//						a.add("element-" + Integer.toString(b));
//						return a;},
//	 (a, b) -> null);
//	System.out.println(result);