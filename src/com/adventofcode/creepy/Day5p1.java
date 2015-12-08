package com.adventofcode.creepy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Day5p1 {
	static String vowels = "aeiuo";
	static String[] forbidden = {"ab", "cd", "pq", "xy"};
	
	public static void main(String[] args) throws IOException {
		int niceCount = 0;
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(Day5p1.class.getResourceAsStream("/input-day5.txt")));) {
			String line;
			while ((line = reader.readLine()) != null) {
				if (isNice(line)) {
					niceCount++;
				}
			}
		}
		System.out.println(niceCount);
		
	}

	public static boolean isNice(String word) {
		int vowelCount = 0;
		boolean twice = false;
		int len = word.length();
		
		for (int i = 0; i < len; i++) {
			if (isVowel(word.charAt(i))) {
				vowelCount++;
			}
			
			if (i < len - 1) {
				if (!twice) {
					twice = word.charAt(i) == word.charAt( i + 1);
				}
				
				if (isForbidden(word.substring(i, i + 2))) {
					return false;
				}
				
			}
		}
		
		return vowelCount >= 3 && twice;
	}
	
	public static boolean isVowel(char c) {
		for (char a: vowels.toCharArray()) {
			if (c == a) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isForbidden(String letters) {
		for (String s: forbidden) {
			if (letters.equals(s)) {
				return true;
			}
		}
		return false;
	}
}
