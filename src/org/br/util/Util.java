package org.br.util;

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
}
