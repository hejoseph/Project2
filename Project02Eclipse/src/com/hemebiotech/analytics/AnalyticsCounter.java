package com.hemebiotech.analytics;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AnalyticsCounter {
	private static int headacheCount = 0;	// initialize to 0
	private static int rashCount = 0;		// initialize to 0
	private static int pupilCount = 0;		// initialize to 0
	
	public static void main(String args[]){
		try {
		// first get input
			BufferedReader reader = new BufferedReader (new FileReader("symptoms.txt"));
			String line = reader.readLine();
	
			int i = 0;	// set i to 0
			int headCount = 0;	// counts headaches
			while (line != null) {
				i++;	// increment i
				System.out.println("symptom from file: " + line);
				if (line.equals("headache")) {
					headCount++;
					System.out.println("number of headaches: " + headCount);
				}
				else if (line.equals("rush")) {
					rashCount++;
				}
				else if (line.contains("pupils")) {
					pupilCount++;
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
			writer.write("headache: " + headacheCount + "\n");
			writer.write("rash: " + rashCount + "\n");
			writer.write("dialated pupils: " + pupilCount + "\n");
			writer.close();
		}catch(IOException ioe) {
			System.out.println("cannot write output file");
		}
	}
}
