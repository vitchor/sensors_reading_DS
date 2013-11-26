package org.br.thread;

import java.util.ArrayList;

import org.br.corbaSupport.client.Client;
import org.omg.CORBA.Any;
import org.omg.CORBA.ORB;

public class ClientUpdater implements Runnable {

	private ArrayList<String> temperatureBuffer;
	private ArrayList<String> pressureBuffer;
	private ArrayList<String> humidityBuffer;

	private ArrayList<Client> temperatureClients;
	private ArrayList<Client> pressureClients;
	private ArrayList<Client> humidityClients;

	private ORB orb;

	public ClientUpdater(ORB orb) {
		temperatureBuffer = new ArrayList<String>();
		pressureBuffer = new ArrayList<String>();
		humidityBuffer = new ArrayList<String>();

		temperatureClients = new ArrayList<Client>();
		pressureClients = new ArrayList<Client>();
		humidityClients = new ArrayList<Client>();

		this.orb = orb;
	}

	public synchronized void appendTemperature(String temperature) {
		temperatureBuffer.add(temperature);
	}

	public synchronized void appendPressure(String pressure) {
		pressureBuffer.add(pressure);
	}

	public synchronized void appendHumidity(String humidity) {
		humidityBuffer.add(humidity);
	}

	public synchronized void appendTemperatureClient(Client client) {
		temperatureClients.add(client);
	}

	public synchronized void appendPressureClient(Client client) {
		pressureClients.add(client);
	}

	public synchronized void appendHumidityClient(Client client) {
		humidityClients.add(client);
	}

	@Override
	public void run() {
		while (true) {

			try {
				Thread.sleep(1000);

				synchronized (temperatureBuffer) {
					for (Client client : temperatureClients) {
						final Any temperatureAny = orb.create_any();
						temperatureAny.insert_Value(temperatureBuffer);
						client.updateTagValues("T", temperatureAny);
					}
				}

				synchronized (pressureBuffer) {
					for (Client client : pressureClients) {
						final Any pressureAny = orb.create_any();
						pressureAny.insert_Value(pressureBuffer);
						client.updateTagValues("P", pressureAny);
					}
				}

				synchronized (humidityBuffer) {
					for (Client client : humidityClients) {
						final Any humidityAny = orb.create_any();
						humidityAny.insert_Value(humidityBuffer);
						client.updateTagValues("H", humidityAny);
					}
				}
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
