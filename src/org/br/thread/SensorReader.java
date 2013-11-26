package org.br.thread;

import org.br.corbaSupport.service.Service;
import org.br.gui.SensorGUI;
import org.br.util.Reader;

public class SensorReader implements Runnable {

	private String name;
	private String tag;
	private SensorGUI sensorGUI;
	private Service service;

	public SensorReader(String name, String tag, SensorGUI sensorGUI,
			Service service) {
		this.name = name;
		this.tag = tag;
		this.sensorGUI = sensorGUI;
		this.service = service;
	}

	@Override
	public void run() {
		int index = 0;
		String tag_values = ")";
		
		while (true) {
			try {
				Thread.sleep(1000);

				final String tag_value = Reader.readSensorFile(tag, index);

				if (tag_values.equals(")")) {
					tag_values = tag_value + tag_values;
				} else {
					tag_values = tag_value + ", " + tag_values;
				}

				sensorGUI.setValueLabel(tag_value);

				index = index + 1;

				if (index % 5 == 0) {
					tag_values = "(" + tag_values;
					service.updateSensorValues(name, tag, tag_values);
					tag_values = ")";
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
