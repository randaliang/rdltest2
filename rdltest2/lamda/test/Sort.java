package test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sort {

	public static void main( String args[] ) {
		List<String> list = new ArrayList<>();
		list.sort((s1,s2)->Integer.compare(s1.length(), s2.length()));
		list.sort((s1,s2)-> { return s1.length() - s2.length() ;});
		list.sort(Comparator.comparing(String::length));
		
		Map<String,Integer> map = new HashMap<>();
		map.merge("1", 1, Integer::sum);
	}
	
}
