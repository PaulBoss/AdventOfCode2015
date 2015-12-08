package com.adventofcode.creepy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Day3p2 {
	public static void main(String[] args) {		
		Set<String> houses = new HashSet<>();
		
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(Day3p2.class.getResourceAsStream("/input-day3.txt")));) {
			String line;
			int x = 0; 
			int y = 0;
			int x1 = 0;
			int y1 = 0;
			
			houses.add(x + "," + y);
			boolean santa = true;
			
			while ((line = reader.readLine()) != null) {
				for (int i = 0; i < line.length(); i++) {
					int ix = 0;
					int iy = 0;
					
					switch (line.charAt(i)) {
					case '^': iy = -1; break;
					case '>': ix = 1; break;
					case 'v': iy = 1; break;
					case '<': ix = -1; break;
					}
					
					if (santa) {
						x = x + ix;
						y = y+ iy;
						houses.add(x + "," + y);	
					} else {
						x1 = x1 + ix;
						y1 = y1 + iy;
						houses.add(x1 + "," + y1);
					}
					
					santa = !santa;
					
				}
			}
			
			System.out.println(houses.size());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
