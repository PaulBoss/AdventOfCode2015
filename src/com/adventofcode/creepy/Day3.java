package com.adventofcode.creepy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Day3 {
	public static void main(String[] args) {		
		Set<String> houses = new HashSet<>();
		
		try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/input-day3.txt")));) {
			String line;
			int x = 0; 
			int y = 0;
			houses.add(x + "," + y);
			
			while ((line = reader.readLine()) != null) {
				for (int i = 0; i < line.length(); i++) {
					switch (line.charAt(i)) {
					case '^': y--; break;
					case '>': x++; break;
					case 'v': y++; break;
					case '<': x--; break;
					}
					houses.add(x + "," + y);
				}
			}
			
			System.out.println(houses.size());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
