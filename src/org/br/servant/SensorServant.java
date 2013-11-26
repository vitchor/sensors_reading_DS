package org.br.servant;

import org.br.corbaSupport.sensor.SensorPOA;
import org.br.corbaSupport.service.Service;
import org.br.gui.SensorGUI;
import org.br.thread.SensorReader;

public class SensorServant extends SensorPOA {

	private Service service;
	private String name;
	private String tag;
	private SensorGUI sensorGUI;

	public SensorServant(String name, String tag, Service service) {
		super();

		this.service = service;
		this.tag = tag;
		this.name = name;

		sensorGUI = new SensorGUI(name, tag);
		sensorGUI.setVisible(true);

		final SensorReader sensorReader = new SensorReader(this.name, this.tag,
				this.sensorGUI, this.service);

		final Thread sensorReaderThread = new Thread(sensorReader);

		sensorReaderThread.start();

//		try {
//			sensorReaderThread.join();
//
//		} catch (InterruptedException e) {
//			System.out.println("Falhou no join!");
//			e.printStackTrace();
//		}
	}
}
