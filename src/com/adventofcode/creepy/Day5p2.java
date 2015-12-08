package com.adventofcode.creepy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Day5p2 {

	public static void main(String[] args) throws IOException {
		int niceCount = 0;
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(Day5p2.class.getResourceAsStream("/input-day5.txt")));) {
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
		boolean repeated = false;
		boolean repeatedPair = false;
		int len = word.length();
		
		for (int i = 0; i < len; i++) {
			if (!repeated && i < len - 2) {
				repeated = word.charAt(i) == word.charAt( i + 2);		
			}
			
			if (!repeatedPair && i < len - 1) {
				 int index = word.indexOf(word.substring(i, i + 2));
				 repeatedPair = index != -1 && index != i && (Math.abs(i - index) > 1);
			}
		}
		
		return repeated && repeatedPair;
	}
}
