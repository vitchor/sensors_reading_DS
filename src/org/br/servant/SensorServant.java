package org.br.servant;

import java.util.Timer;
import java.util.TimerTask;

import org.br.corbaSupport.sensor.SensorPOA;
import org.br.corbaSupport.service.Service;
import org.br.util.Reader;

public class SensorServant extends SensorPOA {

	private Service service;
	private String name;
	private String tag;
	private Timer timer;

	public SensorServant(String name, String tag, Service service) {
		super();

		this.service = service;
		this.tag = tag;
		this.name = name;

		timer = new Timer();
		timer.schedule(new UpdateValues(), 5000);
	}

	class UpdateValues extends TimerTask {

		int index = 0;

		@Override
		public void run() {

			String tag_values = Reader.readSensorFile(tag, index);
			service.updateSensorValues(name, tag, tag_values);

			index = index + 1;
		}
	}
}
