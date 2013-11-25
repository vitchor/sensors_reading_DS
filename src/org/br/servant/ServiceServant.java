package org.br.servant;

import java.util.ArrayList;

import org.br.corbaSupport.client.Client;
import org.br.corbaSupport.client.ClientHelper;
import org.br.corbaSupport.service.ServicePOA;
import org.br.gui.ServiceGUI;
import org.br.util.Buffer;
import org.br.util.Util;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;

public class ServiceServant extends ServicePOA {

	private ServiceGUI serviceGUI;

	private NamingContext namingContext;

	private Buffer temperatureBuffer;
	private Buffer pressureBuffer;
	private Buffer humidityBuffer;

	private ArrayList<Client> temperatureClients;
	private ArrayList<Client> pressureClients;
	private ArrayList<Client> humidityClients;

	public ServiceServant() {
		super();

		temperatureBuffer = new Buffer();
		pressureBuffer = new Buffer();
		humidityBuffer = new Buffer();

		temperatureClients = new ArrayList<Client>();
		pressureClients = new ArrayList<Client>();
		humidityClients = new ArrayList<Client>();

		this.serviceGUI = new ServiceGUI();
		this.serviceGUI.setVisible(true);
	}

	@Override
	public int updateSensorValues(String sensorName, String tag,
			String tagValues) {

		final String valueFinalFormat = createPersistentValue(tag, sensorName,
				tagValues);

		if (tag.equals("T")) {
			temperatureBuffer.append(valueFinalFormat);
			sendValueToClientArray(valueFinalFormat, temperatureClients, tag);

		} else if (tag.equals("P")) {
			pressureBuffer.append(valueFinalFormat);
			sendValueToClientArray(valueFinalFormat, pressureClients, tag);

		} else if (tag.equals("H")) {
			humidityBuffer.append(valueFinalFormat);
			sendValueToClientArray(valueFinalFormat, humidityClients, tag);

		} else {
			return 0;
		}

		serviceGUI.setclientLabelText(valueFinalFormat);

		return 1;
	}

	@Override
	public int registerClient(String client_name, String first_tag,
			String second_tag) {
		try {

			final NameComponent[] clientName = { new NameComponent(client_name,
					"Object") };
			final Client client = ClientHelper.narrow(namingContext
					.resolve(clientName));

			if (first_tag.equals("T") || second_tag.equals("T")) {
				temperatureClients.add(client);
			}
			if (first_tag.equals("P") || second_tag.equals("P")) {
				pressureClients.add(client);
			}
			if (first_tag.equals("H") || second_tag.equals("H")) {
				humidityClients.add(client);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	private String createPersistentValue(String tag, String sensorName,
			String tagValues) {
		final String measurementUnit = Util.getMeasurementUnit(tag);
		final String timeString = Util.getTimestamp();

		return "<" + sensorName + "> - " + tagValues + measurementUnit + " - ["
				+ timeString + "]";
	}

	private void sendValueToClientArray(String tagValue,
			ArrayList<Client> arrayList, String tag) {

		for (Client client : arrayList) {
			client.updateTagValues(tag, tagValue);
		}
	}
}
