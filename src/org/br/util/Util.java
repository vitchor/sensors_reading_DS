package org.br.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

	public static String getMeasurementUnit(String tag) {

		String measurementUnit = "";

		if (tag.equals("T")) {
			measurementUnit = "¡C";
			
		} else if (tag.equals("P")) {
			measurementUnit = "Pa";
			
		} else if (tag.equals("H")) {
			measurementUnit = "%";
		}

		return measurementUnit;
	}

	public static String getTimestamp() {
		final SimpleDateFormat timeFormat = new SimpleDateFormat(
				"dd/MM/yyyy HH:mm:ss");
		return timeFormat.format(new Date());
	}
}
