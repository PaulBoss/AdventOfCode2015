package com.adventofcode.creepy;

import java.io.IOException;

public class Day10 {
	public static void main(String[] args) throws IOException {
		String input = "1113122113";
		StringBuilder output = new StringBuilder(); 
		
		for (int k = 0; k < 50; k++) {
			int count = 1;
			char current = input.charAt(0);
			for (int i = 1; i < input.length(); i++) {
				if (current != input.charAt(i)) {
					output.append(count);
					output.append(current);
					current = input.charAt(i);
					count = 0;
				}
				count++;
			}
	
			output.append(count);
			output.append(current);
			System.out.println(k + ": " + output.length());
			input = output.toString();
			output = new StringBuilder();
		}
		
		
	}
}
