package com.adventofcode.creepy;

public class Day11 {
	public static void main(String[] args) {
		String input = "vzbxkghb";
		char[] chars = input.toCharArray();

		chars = getNextPassword(chars);
		System.out.println(chars);
		chars = getNextPassword(chars);
		System.out.println(chars);
	}

	private static char[] getNextPassword(char[] chars) {
		boolean valid = false;
		while (!valid) {	
			int i = chars.length - 1;
			
			chars[i] = (char) (chars [i] + 1);
			
			while (chars[i] > 'z' && i > 0) {
				chars[i] = 'a';
				i--;
				chars[i] = (char) (chars [i] + 1);
			}
			
			if (chars[0] > 'z') {
				chars[0] = 'a';
				
				char[] newchars = new char[chars.length + 1];
				newchars[0] = 'a';
				for (int j = 0; j < chars.length; j++) {
					newchars[j + 1] = chars[j];
				}
				chars = newchars;
			}
			valid = isValid(chars);
		}
		return chars;
	}
	
	public static boolean isValid(char[] chars) {
		boolean inc3 = false;
		int pairs = 0;
	    int lastpair = -2;
		
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] == 'i' || chars[i] == 'o' || chars[i] == 'l')
				return false;
			
			if (!inc3 && i < chars.length - 2) {
				inc3 = chars[i] == (chars[i + 1] - 1) && chars[i] == (chars[i + 2] - 2);
			}
			if (pairs < 2 && lastpair < i - 1 && i < chars.length - 1) {
				if (chars[i] == chars[i + 1]) {
					pairs++;
					lastpair = i;
				}
			}
		}
		
		return inc3 && pairs == 2;
	}
}
