package com.adventofcode.creepy;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Day6p2 {
	static int[][] grid = new int[1000][1000];
	
	public static void main(String[] args) throws IOException {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(Day3p2.class.getResourceAsStream("/input-day6.txt")));) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(" ");
				if (parts.length == 4) {
					setState(parts[1], parts[3], false, true);
				} else {
					boolean state = parts[1].equals("on");
					setState(parts[2], parts[4], state, false);
				}
				
			}
		}
		
		long count = 0;
		for (int y = 0; y < 1000; y++) {
			for (int x = 0; x < 1000; x++) {
				count +=(grid[x][y]);
			}
		}
		System.out.println(count);
	}
	
	private static Point getPoint(String point) {
		String[] parts = point.split(",");
		return new Point(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
	}
	
	private static void setState(String start, String end, boolean state, boolean toggle) {
		Point p1 = getPoint(start);
		Point p2 = getPoint(end);
		
		for (int y = p1.y; y <= p2.y; y++) {
			for (int x = p1.x; x <= p2.x; x++) {
				if (toggle) {
					grid[x][y] += 2;
				} else if (state) {
					grid[x][y]++;
				} else if (grid[x][y] > 0) {
					grid[x][y]--;
				}
			}
		}
		
	}
}
