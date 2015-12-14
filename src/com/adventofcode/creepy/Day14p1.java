package com.adventofcode.creepy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import jdk.nashorn.internal.objects.annotations.SpecializedFunction;

public class Day14p1 {
	private static class DeerInfo {
		public int speed;
		public int speedSustained;
		public int restTime;
		public boolean resting = false;
		
		public int distance = 0;
		public int speedingFor = 0; 
		public int restingFor = 0;
	}

	public static void main(String[] args) throws IOException {
		Map<String, DeerInfo> deers = new HashMap<>();
		
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(Day14p1.class.getResourceAsStream("/input-day14.txt")));) {
			String line;
			
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(" ");
				String name = parts[0];
				DeerInfo info = new DeerInfo();
				info.speed = Integer.parseInt(parts[3]);
				info.speedSustained = Integer.parseInt(parts[6]);
				info.restTime = Integer.parseInt(parts[13]);
				deers.put(name, info);
			}
		}
		
		for (int i = 0; i < 2503 ; i++) {
			for (Entry<String, DeerInfo> entry: deers.entrySet()) {				
				DeerInfo info = entry.getValue();
				if (!info.resting) {
					info.distance += info.speed;
					info.speedingFor++;
					
					info.resting = info.speedingFor == info.speedSustained;
					
				} else {
					info.restingFor++;
					
					if (info.restingFor == info.restTime) {
						info.resting = false;
						info.restingFor = 0;
						info.speedingFor = 0;
					}
				}
			}
		}
		
		for (Entry<String, DeerInfo> entry: deers.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue().distance);	
		}
	}
	
	
	
}
