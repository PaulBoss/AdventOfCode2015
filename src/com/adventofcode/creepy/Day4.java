package com.adventofcode.creepy;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Day4 {
	public static void main(String[] args) throws NoSuchAlgorithmException  {
		String key = "yzbqklnj";

		MessageDigest digestAlgo = MessageDigest.getInstance("MD5");
		
		long i = -1;
		String result = "";
		while (!result.startsWith("00000")) {
			i++;
			String txt = key + i;
			result = javax.xml.bind.DatatypeConverter.printHexBinary(digestAlgo.digest(txt.getBytes(StandardCharsets.UTF_8)));	
		}
		
		System.out.println(i);
		
		i = -1;
		result = "";
		while (!result.startsWith("000000")) {
			i++;
			String txt = key + i;
			result = javax.xml.bind.DatatypeConverter.printHexBinary(digestAlgo.digest(txt.getBytes(StandardCharsets.UTF_8)));	
		}
		
		
		System.out.println(i);

	}
	
}
