package com.adventofcode.creepy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.json.JSONArray;
import org.json.JSONObject;

public class Day12p2 {
	public static void main(String[] args) throws IOException {

		StringBuilder json = new StringBuilder();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(Day12p2.class.getResourceAsStream("/input-day12.txt")));) {
			String line;
			while ((line = reader.readLine()) != null) {
				json.append(line);
			}
		}
		
		JSONArray array = new JSONArray(json.toString());
		
		
		System.out.println(parseArray(array));
	}
	
	public static int parseIt(Object value) {
		int result = 0;
		if (value instanceof JSONArray) {
			result += parseArray((JSONArray) value);
		} else if (value instanceof JSONObject) {
			result += parseObject((JSONObject) value);
		} else if (value instanceof Integer ) { 
			result += (Integer) value;
		}
		
		
		return result;
	}
	
	public static int parseObject(JSONObject object) {
		int result = 0;
		for (String key: object.keySet()) {
			Object value = object.get(key);

			if (value instanceof String) {
				if (((String) value).equals("red")) {
					return 0;
				}
			}
			result += parseIt(value);
			
		}
		return result;
	}
	
	public static int parseArray(JSONArray array) {
		int result = 0;
		for (int i = 0; i < array.length(); i++) {
			Object value = array.get(i);
			result += parseIt(value);
			
		}
		return result;
	}
}
		