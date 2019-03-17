package test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.util.stream.Collectors.groupingBy;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Stream;


public class Anagrams2 {
	public static void main(String[] args) throws IOException {
		Path dictionary = Paths.get("D:\\temp\\江西中行\\20190219\\readme.txt");
		int minGroupSize = Integer.parseInt("1");
		try (Stream<String> words = Files.lines(dictionary)) {
			
			words.collect(groupingBy(word -> word.chars().sorted().collect(StringBuilder::new, (sb, c) -> sb.append((char) c), StringBuilder::append).toString()))
					.values().stream().filter(group -> group.size() >= minGroupSize)
					.map(group -> group.size() + ": " + group).forEach(System.out::println);
		}
	}
	
	public void test() {
//		   BiConsumer<StringBuilder, StringBuilder>  m =  (new StringBuilder())::append ;
//		   Function<Integer,Integer> a = x-> x+5 ;
		
		StringBuilder sb = new StringBuilder();
//		BiConsumer<String, String> combiner = sb::append;
		

	}
	
	
}