package com.adventofcode.creepy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Day2 {

	public static void main(String[] args) {		
		try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/input-day2.txt")));) {
			long paper = 0;
			long ribbon = 0;
			
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split("x");
				int w = Integer.parseInt(parts[0]);
				int h = Integer.parseInt(parts[1]);
				int l = Integer.parseInt(parts[2]);
				
				paper += area(w, h, l) + smallest( l*w, l*h, w*h);
				ribbon += shortestPath(w, h, l) + volume(w, h, l);
			}
			
			System.out.println("Paper: " + paper);
			System.out.println("Ribbon: " + ribbon);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static long area(int w, int h, int l) {
		return 2*l*w + 2*l*h + 2*w*h;
	}
	private static int smallest(int ...args) {
		int result = args[0];
		for (int i = 1; i < args.length; i++) {
			if (args[i] < result) {
				result = args[i];
			}
		}
		return result;
	}
	
	private static int shortestPath(int w, int h, int l) {
		if (l >= w && l >= h) {
			return w + w + h + h;
		} else if (w >= h && w >= l) {
			return h + h + l + l;
		} else {
			return w + w + l + l;
		}
	}
	
	private static int volume(int w, int h, int l) {
		return w*h*l;
	}
}
