package test;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static java.util.Comparator.comparing;

public class Prime {
	public static void main(String[] args) {
		Map<String,Long> freq = new HashMap<>();
		List<String> topTen = freq.keySet().stream()
				.sorted(comparing(freq::get).reversed())
				.limit(10)
				.collect(toList());
		
		freq.keySet().stream().iterator();
	}
}

