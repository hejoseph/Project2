package com.hemebiotech.analytics;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AnalyticsCounter {
	private static int headacheCount = 0;	// initialize to 0
	private static int rashCount = 0;		// initialize to 0
	private static int pupilCount = 0;		// initialize to 0
	
	public static void main(String args[]){
		
		HashMap<String,Integer> counter = new HashMap<String,Integer>();
		
		try {
		// first get input
			BufferedReader reader = new BufferedReader (new FileReader("symptoms.txt"));
			String line = reader.readLine();
	
			int headCount = 0;	// counts headaches
			while (line != null) {
				System.out.println("symptom from file: " + line);
				
				if(counter.containsKey(line)) {
					int value = counter.get(line)+1;
					counter.put(line, value);
				}else {
					counter.put(line, 1);
				}
				
				line = reader.readLine();	// get another symptom
			}
			reader.close();
		}catch(FileNotFoundException fnfe) {
			System.out.println("file not found exception "+fnfe.getMessage());
		}catch(IOException ioe) {
			System.out.println("cannot read input file");
		}
		
		try {
			// next generate output
			FileWriter writer = new FileWriter ("result.out");
			for(Map.Entry<String, Integer> entry : counter.entrySet()) {
				writer.write(entry.getKey()+": " + entry.getValue() + "\n");
			}
			writer.close();
		}catch(IOException ioe) {
			System.out.println("cannot write output file");
		}
	}
}
