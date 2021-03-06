package com.hemebiotech.analytics;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Used to count symptoms from input file, and generate an output file
 * @author HE Joseph
 *
 */
public class AnalyticsCounter {
	
	private String filePath; 
	private ISymptomReader reader;
	private HashMap<String,Integer> counter;
	
	public AnalyticsCounter(String filePath) {
		this.filePath = filePath;
		this.reader = new ReadSymptomDataFromFile(this.filePath);
		this.counter = counter = new HashMap<String,Integer>();
	}
	
	/**
	 * count symptoms
	 */
	private void countSymptoms() {
		List<String> symptoms = reader.GetSymptoms();
		for(String symptom : symptoms){
			if(counter.containsKey(symptom)) {
				int value = counter.get(symptom)+1;
				counter.put(symptom, value);
			}else {
				counter.put(symptom, 1);
			}
		}
	}
	
	/**
	 * Generate an output file after counting
	 * @param outputName
	 */
	public void generateCountFile(String outputName) {
		countSymptoms();
		try {
			// next generate output
			FileWriter writer = new FileWriter(outputName);
			
			Map<String, Integer> sortedCounter = new TreeMap<String, Integer>(counter);
			
			for(Map.Entry<String, Integer> entry : sortedCounter.entrySet()) {
				writer.write(entry.getKey()+": " + entry.getValue() + "\n");
			}
			writer.close();
		}catch(IOException ioe) {
			System.out.println("cannot write output file");
		}
	}
	
	public static void main(String args[]){
		AnalyticsCounter counter = new AnalyticsCounter("symptoms.txt");
		counter.generateCountFile("results.out");
	}
}
