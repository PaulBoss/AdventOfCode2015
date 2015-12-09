package com.adventofcode.creepy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Day9p2 {
	
	private static Map<String, Map<String, Integer>> distances = new HashMap<>();
	
	
	public static void main(String[] args) throws IOException {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(Day9p2.class.getResourceAsStream("/input-day9.txt")));) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(" to ");
				String[] parts2 = parts[1].split(" = ");
				
				String placeFrom = parts[0];
				String placeTo = parts2[0];
				int distance = Integer.parseInt(parts2[1]);
				
				putOnMap(placeFrom, placeTo, distance);
				putOnMap(placeTo, placeFrom, distance);
			}
		}
		
		String[] places = distances.keySet().toArray(new String[0]);
		
		System.out.println(findIt(places, 0, 0));
	}

	private static void putOnMap(String placeFrom, String placeTo, int distance) {
		Map<String, Integer> distanceMap = distances.get(placeFrom);
		if (distanceMap == null) {
			distanceMap = new HashMap<>();
		}
		distanceMap.put(placeTo, distance);
		distances.put(placeFrom, distanceMap);
	}

	private static int findIt(String[] places, int depth, int currentLength) {
		if (depth == places.length - 1) {
			return calcLength(places);
		}
		
		int len = findIt(places, depth + 1, currentLength);
		if (len > currentLength) {
			currentLength = len;
		}

		for (int i = depth + 1; i < places.length; i++) {
			swap(places, depth, i);
			len = findIt(places, depth + 1, currentLength);
			if (len > currentLength) {
				currentLength = len;
			}
			swap(places, i, depth);		
		}
		
		return currentLength;
	}
	
	private static void swap(String[] places, int i1, int i2) {
		String t = places[i1];
		places[i1] = places[i2];
		places[i2] = t;
	}
	
	private static int calcLength(String[] places) {
		int len =0;
		for (int i = 0; i < places.length - 1; i++) {
			len += distances.get(places[i]).get(places[i + 1]);
		}
		
		return len;
	}
	
}
