package com.adventofcode.creepy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class Day8 {
	public static void main(String[] args) throws IOException {
		int total = 0;
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(Day3p2.class.getResourceAsStream("/input-day8.txt")));) {
			String line;
			Pattern p = Pattern.compile("\\\\\\\\|\\\\x[0-9a-f]{2}|\\\\\"", Pattern.CASE_INSENSITIVE);
			while ((line = reader.readLine()) != null) {			
				int codeLen = line.trim().length();	
				int charLen = p.matcher(line).replaceAll("1").length() - 2;			
				total += codeLen - charLen;
			}
			System.out.println(total);
		}
		
		total = 0;
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(Day3p2.class.getResourceAsStream("/input-day8.txt")));) {
			String line;
			Pattern p = Pattern.compile("\\\\|\"", Pattern.CASE_INSENSITIVE);
			while ((line = reader.readLine()) != null) {			
				int codeLen = line.trim().length();	
				int charLen = p.matcher(line).replaceAll("22").length() + 2;
				total += charLen - codeLen;
			}
			System.out.println(total);
		}
	}
}
