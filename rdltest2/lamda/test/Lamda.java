package test;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javax.swing.JButton;

import org.junit.Test;

import com.google.common.collect.Lists;

public class Lamda {

	@Test
	public void test() {
		File f = new File("d:\\temp");
		File[] fileList = f.listFiles((pathname) -> {
			if (pathname.isDirectory()) {
				return true;
			}
			String fileName = pathname.getName();
			return fileName.endsWith(".doc");
		});
		for( File file : fileList ) {
			System.out.println(file.getAbsolutePath());
		}
	}
	
	@Test
	public void test1() {
		File f = new File("d:\\temp");
		File[] fileList = f.listFiles((pathname) -> {
			if (pathname.isDirectory()) {
				return true;
			}
			String fileName = pathname.getName();
			return fileName.endsWith(".doc");
		});
		for( File file : fileList ) {
			System.out.println(file.getAbsolutePath());
		}
	}
	
	@Test
	public void TestThread() {
		new Thread(()-> {System.out.println("run1");}).start();
		new Thread() {
			public void run() {
				System.out.println("run2");
			}
		}.start();
	}
	
	@Test
	public void testFile() {
		File f = new File("d:\\temp");
		File[] fileList = f.listFiles((a,b) -> {
			System.out.println("testFile===" + a.getAbsolutePath() + "=======" +b);
			return true;
		});
		for( File file : fileList ) {
			System.out.println(file.getAbsolutePath());
		}
	}
	
	@Test
	public void testActionPeformed() {
		JButton show = new JButton("Show");
		show.addActionListener((e) -> {
			System.out.println("Event handling without lambda expression is boring");
		});
	}
	
	@Test
	public void testStream() {
		List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
		features.forEach(n ->{System.out.println("teststream "+n);});
		features.forEach(String::toString);
	}

	@Test
	public  void main() {
		List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
		System.out.println("Languages which starts with J :");
		filter(languages, (str) -> str.startsWith("J"));
		System.out.println("Languages which ends with a ");
		filter(languages, (str) -> str.endsWith("a"));
		System.out.println("Print all languages :");
		filter(languages, (str) -> true);
		System.out.println("Print no language : ");
		filter(languages, (str) -> false);
		System.out.println("Print language whose length greater than 4:");
		filter(languages, (str) -> str.length() > 4);
	}

	public static void filter(List<String> names, Predicate<String> condition) {
		for (String name : names) {
			if (condition.test(name)) {
				System.out.println(name + " ");
			}
		}
	}
	
	@Test
	public void testCost(){
		// 不使用lambda表达式为每个订单加上12%的税
		List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
		for (Integer cost : costBeforeTax) {
			double price = cost + .12 * cost;
			System.out.println(price);
		} 
//		 使用lambda表达式
		 List<Integer> costBeforeTax1 = Arrays.asList(100, 200, 300, 400,500);
		 costBeforeTax1.stream().map((cost) -> cost + 0.12*cost).forEach(System.out::println);
	}
	
	@Test
	public void testReduce() {
//		// 为每个订单加上12%的税// 老方法：
//		List costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
//		double total = 0;
//		for (Integer cost : costBeforeTax) {
//			double price = cost + .12 * cost;
//			total = total + price;
//		}
//		System.out.println("Total : " + total);
		List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
		double bill = costBeforeTax.stream()
				.map((cost) -> cost + .12 * cost)
				.reduce((sum, cost) -> sum + cost)
				.get();
		System.out.println("Total : " + bill);
	}
	
	@Test
	public void testCollect() {
		// 创建一个字符串列表，每个字符串长度大于2
		List<String> strList = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");

		List<String> filtered = strList.stream()
				.filter(x -> x.length()> 2)
				.collect(Collectors.toList());
		System.out.printf("Original List : %s, filtered list : %s %n", strList, filtered);
	}
	
	public void testSupllier( Supplier s ) {
		
	}
	
	public void test2() {
		testSupllier(ArrayList::new);
	}
	
	@Test
	public void testCollect2() {
		final ArrayList<Dish> dishes = Lists.newArrayList(
			    new Dish("pork", false, 800, Type.MEAT),
			    new Dish("beef", false, 700, Type.MEAT),
			    new Dish("chicken", false, 400, Type.MEAT),
			    new Dish("french fries", true, 530, Type.OTHER),
			    new Dish("rice", true, 350, Type.OTHER),
			    new Dish("season fruit", true, 120, Type.OTHER),
			    new Dish("pizza", true, 550, Type.OTHER),
			    new Dish("prawns", false, 300, Type.FISH),
			    new Dish("salmon", false, 450, Type.FISH)
			);
		
		Optional<Dish> mostCalorieDish = dishes.stream().max(Comparator.comparingInt(Dish::getPrice));
//		Optional<Dish> minCalorieDish = dishes.stream().min(Comparator.comparingInt(Dish::getCalories));
		Double avgCalories = dishes.stream().collect(Collectors.averagingInt(Dish::getPrice));
		IntSummaryStatistics summaryStatistics = dishes.stream().collect(Collectors.summarizingInt(Dish::getPrice));
		double average = summaryStatistics.getAverage();
		long count = summaryStatistics.getCount();
		int max = summaryStatistics.getMax();
		int min = summaryStatistics.getMin();
		long sum = summaryStatistics.getSum();
		
		//直接连接
		String join1 = dishes.stream().map(Dish::getFruit).collect(Collectors.joining());
		System.out.println("=======" + join1);
		//逗号
		String join2 = dishes.stream().map(Dish::getFruit).collect(Collectors.joining(", "));
		System.out.println("=======" + join2);
		
	}
}
