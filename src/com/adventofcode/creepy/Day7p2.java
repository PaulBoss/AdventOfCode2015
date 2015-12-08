package com.adventofcode.creepy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Day7p2 {

	public static Integer getValue(String part) {
		try {
			return Integer.parseInt(part);
		} catch (NumberFormatException e) {
			return wireValue.get(part);
		}
	}
	
	
	static enum Operator {
		AND {
			public Integer calcValue(String cmd) {
				String[] parts = cmd.split(" AND ");
				Integer i1 = getValue(parts[0]);
				Integer i2 = getValue(parts[1]);
				if (i1 != null && i2 != null) {
					return i1 & i2;
				}
				return null;
			}
		},
		OR {
			public Integer calcValue(String cmd) {
				String[] parts = cmd.split(" OR ");
				Integer i1 = getValue(parts[0]);
				Integer i2 = getValue(parts[1]);
				if (i1 != null && i2 != null) {
					return i1 | i2;
				}
				return null;
			}
		},
		LSHIFT{
			public Integer calcValue(String cmd) {
				String[] parts = cmd.split(" LSHIFT ");
				Integer i1 = getValue(parts[0]);
				Integer i2 = Integer.parseInt(parts[1]);
				if (i1 != null && i2 != null) {
					return i1 << i2;
				}
				return null;
			}
		},
		RSHIFT {
			public Integer calcValue(String cmd) {
				String[] parts = cmd.split(" RSHIFT ");
				Integer i1 = getValue(parts[0]);
				Integer i2 = Integer.parseInt(parts[1]);
				if (i1 != null && i2 != null) {
					return i1 >>> i2;
				}
				return null;
			}
		},
		NOT	{
			public Integer calcValue(String cmd) {
				String[] parts = cmd.split(" ");
				Integer i = getValue(parts[1]);
				if (i != null) {
					return ~i;
				}
				return null;
			}
		};
		
		public abstract Integer calcValue(String cmd);
	}
	
	public static Map<String, Integer> wireValue = new HashMap<>();
	public static Map<String, String> wireCmd = new HashMap<>();
	
	public static void main(String[] args) throws IOException {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(Day3p2.class.getResourceAsStream("/input-day7.txt")));) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split("->");
				String wire = parts[1].trim();
				String cmd = parts[0].trim();
				
				boolean hasOp = false;
				for (Operator op: Operator.values()) {
					if (cmd.contains(op.name())) {
						wireCmd.put(wire, cmd);
						hasOp = true;
					}
				}
				if (!hasOp) {
					try {
						wireValue.put(wire, Integer.parseInt(cmd));
					} catch (NumberFormatException e) {
						wireCmd.put(wire, cmd);
					}
				}
			}
			
			// Simple put the answer from Day7p1 in b.... The rest of the code is the same as Day7p1
			wireValue.put("b", 16076);
			
			while (!wireValue.containsKey("a")) {
				System.out.println("values: " + wireValue.size());
				
				for (Entry<String, String> currentWireCmd: wireCmd.entrySet()) {
					String wire = currentWireCmd.getKey();
					String cmd = currentWireCmd.getValue();
										
					if (!wireValue.containsKey(wire)) {
						boolean opFound = false;
						for (Operator op: Operator.values()) {
							if (cmd.contains(op.name())) {
								opFound = true;
								Integer value = op.calcValue(cmd);
								if (value != null) {
									wireValue.put(wire, value & 65535);
								}
							}
						}
						// No operator found. Cmd should be a wire then.
						if (!opFound) {
							Integer value = wireValue.get(cmd);
							if (value != null) {
								wireValue.put(wire, value & 65535);
							}
						}
					}
				}
			}
			
			System.out.println(wireValue.get("a"));
		}
	}
}

