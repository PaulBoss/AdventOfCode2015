package com.adventofcode.creepy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Day13  {

	private static Map<String, Map<String, Integer>> persons = new HashMap<>();
	
	public static void main(String[] args) throws IOException {
	
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(Day1.class.getResourceAsStream("/input-day13.txt")));) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(" ");
				
				String name = parts[0];
				String nextToName = parts[10];
				Integer value = Integer.parseInt(parts[3]);
				Boolean positive = parts[2].equals("gain");
				
				Map<String, Integer> valueMap = persons.get(name);
				if (valueMap == null) {
					valueMap = new HashMap<>();
					persons.put(name, valueMap);
				}
				valueMap.put(nextToName.replaceAll("\\.", ""), positive ? value : -value);
			}
		}
		
		Set<String> keySet = persons.keySet();
		System.out.println(calcMaxHappiness(0, 0, keySet.toArray(new String[0])));
		Set<String> newSet = new HashSet<>();
		newSet.addAll(keySet);
		newSet.add("me");
		
		System.out.println(calcMaxHappiness(0, 0, newSet.toArray(new String[0])));
		
		
	}
	
	private static int calcMaxHappiness(int max, int level, String[] names) {
		if (level == names.length - 1) {
			return calcHappiness(names);
		}
		
		int current = calcMaxHappiness(max, level+1, names);
		if (current > max) {
			max = current;
		}
				
		for (int i = level + 1; i < names.length - 1; i ++) {
			swap(level, i, names);
			current = calcMaxHappiness(max, level+1, names);
			if (current > max) {
				max = current;
			}
			swap(level, i, names);
		}
		
		return max;
	}
	
	private static void swap(int i1, int i2, String[] names) {
		String temp = names[i1];
		names[i1] = names[i2];
		names[i2] = temp;
	}
	
	private static int calcHappiness(String[] names) {
		int result = 0;
		for (int i = 0; i < names.length - 1; i++) {
			if (!names[i].equals("me") && !names[i + 1].equals("me")) {
				result += persons.get(names[i]).get(names[i + 1]);
				result += persons.get(names[i + 1]).get(names[i]);
			}
		}
		
		if (!names[0].equals("me") && !names[names.length - 1].equals("me")) {
			result += persons.get(names[names.length - 1]).get(names[0]);
			result += persons.get(names[0]).get(names[names.length - 1]);
		}
		
		return result;
	}
}
