package com.adventofcode.creepy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Day1 {

	public static int level = 0;

	public static void main(String[] args) {

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(Day1.class.getResourceAsStream("/input-day1.txt")));) {
			
			boolean first = true;
			String line;
			
			while ((line = reader.readLine()) != null) {
				for (int i = 0; i < line.length(); i++) {
					char current = line.charAt(i);
					if (current == '(') {
						level++;
					} else if (current == ')') {
						level--;
						if (level == -1 && first) {
							first = false;
							System.out.println("First basement: " + (i + 1));
						}
					}
				}
			}
			System.out.println("Level: " + level);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
