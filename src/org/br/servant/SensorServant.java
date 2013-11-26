package org.br.servant;

import java.util.Timer;
import java.util.TimerTask;

import org.br.corbaSupport.sensor.SensorPOA;
import org.br.corbaSupport.service.Service;
import org.br.gui.SensorGUI;
import org.br.util.Reader;

public class SensorServant extends SensorPOA {

	private Service service;
	private String name;
	private String tag;
	private Timer timer;
	private SensorGUI sensorGUI;

	public SensorServant(String name, String tag, Service service) {
		super();

		this.service = service;
		this.tag = tag;
		this.name = name;

		sensorGUI = new SensorGUI(name, tag);
		sensorGUI.setVisible(true);

		timer = new Timer();

		timer.scheduleAtFixedRate(new UpdateValues(), 0, 1000);
	}

	class UpdateValues extends TimerTask {

		int index = 0;

		String tag_values = ")";

		@Override
		public void run() {

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

				synchronized (tag_value) {
					service.updateSensorValues(name, tag, tag_values);

				}

				tag_values = ")";
			}
		}
	}
}
