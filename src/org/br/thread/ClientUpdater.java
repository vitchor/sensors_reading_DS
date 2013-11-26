package org.br.thread;

import java.util.ArrayList;

import org.br.corbaSupport.client.Client;

public class ClientUpdater implements Runnable {

	private ArrayList<String> temperatureBuffer;
	private ArrayList<String> pressureBuffer;
	private ArrayList<String> humidityBuffer;

	private ArrayList<Client> temperatureClients;
	private ArrayList<Client> pressureClients;
	private ArrayList<Client> humidityClients;

	public void appendTemperature(String temperature) {
		temperatureBuffer.add(temperature);
	}

	public void appendPressure(String pressure) {
		pressureBuffer.add(pressure);
	}

	public void appendHumidity(String humidity) {
		humidityBuffer.add(humidity);
	}

	public void appendTemperatureClient(Client client) {
		temperatureClients.add(client);
	}

	public void appendPressureClient(Client client) {
		pressureClients.add(client);
	}

	public void appendHumidityClient(Client client) {
		humidityClients.add(client);
	}

	public ClientUpdater() {
		temperatureBuffer = new ArrayList<String>();
		pressureBuffer = new ArrayList<String>();
		humidityBuffer = new ArrayList<String>();

		temperatureClients = new ArrayList<Client>();
		pressureClients = new ArrayList<Client>();
		humidityClients = new ArrayList<Client>();
	}

	@Override
	public void run() {
		while (true) {

			try {
				Thread.sleep(500);

				for (String temperature : temperatureBuffer) {
					sendValueToClientArray(temperature, temperatureClients, "T");
				}
				for (String pressure : pressureBuffer) {
					sendValueToClientArray(pressure, pressureClients, "P");
				}
				for (String humidity : humidityBuffer) {
					sendValueToClientArray(humidity, humidityClients, "H");
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private synchronized void sendValueToClientArray(String tagValue,
			ArrayList<Client> arrayList, String tag) {

		for (Client client : arrayList) {
			client.updateTagValues(tag, tagValue);
		}
	}
}
