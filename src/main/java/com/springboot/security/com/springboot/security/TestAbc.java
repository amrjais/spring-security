package com.springboot.security.com.springboot.security;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class TestAbc {
	public static void main(String[] args) {

		String str = "Today is a Monday";
		Map<Character, Integer> map = new HashMap<>();

		for (int i = 0; i < str.length(); i++) {
			int count = 1;
			if (str.charAt(i) == ' ') {
				continue;
			}
			if (map.get(str.charAt(i)) != null) {
				count += map.get(str.charAt(i));
			}
			map.put(str.charAt(i), count);
		}
		System.out.println(map);
		
		
	}
}
