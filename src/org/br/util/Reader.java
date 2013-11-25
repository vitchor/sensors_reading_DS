package org.br.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Reader {

	public static synchronized String readSensorFile(String tag, int index) {

		String filePath = "";

		if (tag.equals("T")) {
			filePath = "/Users/mac/sensors_reading_DS/resources/Temperature.txt";
		} else if (tag.equals("P")) {
			filePath = "/Users/mac/sensors_reading_DS/resources/Pressure.txt";
		} else if (tag.equals("H")) {
			filePath = "/Users/mac/sensors_reading_DS/resources/Humidity.txt";
		}

		String sensorFileString = "";

		try {
			sensorFileString = readFile(filePath, index);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return sensorFileString;
	}

	public static synchronized String readFile(String filePath, int index)
			throws IOException {

		BufferedReader inputStream = null;
		String line = "";

		try {
			inputStream = new BufferedReader(new FileReader(filePath));

			int lineIndex = 0;

			while ((line = inputStream.readLine()) != null) {
				if (lineIndex == index)
					break;
				lineIndex = lineIndex + 1;
			}

		} catch (Exception e) {
			System.out.println("Error " + e);
			e.printStackTrace(System.out);
		}

		if (inputStream != null) {
			inputStream.close();
		}

		return line;
	}
}