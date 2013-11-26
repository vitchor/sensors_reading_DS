package org.br.servant;

import org.br.corbaSupport.client.Client;
import org.br.corbaSupport.client.ClientHelper;
import org.br.corbaSupport.service.ServicePOA;
import org.br.gui.ServiceGUI;
import org.br.thread.ClientUpdater;
import org.br.util.Util;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;

public class ServiceServant extends ServicePOA {

	private ServiceGUI serviceGUI;

	private NamingContext namingContext;
	private ClientUpdater clientUpdater;

	public ServiceServant(NamingContext namingContext, ORB orb) {
		super();

		this.namingContext = namingContext;

		clientUpdater = new ClientUpdater(orb);

		try {
			final Thread clientUpdaterThread = new Thread(clientUpdater);
			clientUpdaterThread.start();
			//clientUpdaterThread.join();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		this.serviceGUI = new ServiceGUI();
		this.serviceGUI.setVisible(true);
	}

	@Override
	public int updateSensorValues(String sensorName, String tag,
			String tagValues) {

		final String valueFinalFormat = createPersistentValue(tag, sensorName,
				tagValues);

		if (tag.equals("T")) {
			clientUpdater.appendTemperature(valueFinalFormat);

		} else if (tag.equals("P")) {
			clientUpdater.appendPressure(valueFinalFormat);

		} else if (tag.equals("H")) {
			clientUpdater.appendHumidity(valueFinalFormat);

		} else {
			return 0;
		}

		serviceGUI.setclientLabelText(valueFinalFormat);

		return 1;
	}

	@Override
	public synchronized int registerClient(String client_name, String first_tag,
			String second_tag) {
		try {

			final NameComponent[] clientName = { new NameComponent(client_name,
					"Object") };
			final Client client = ClientHelper.narrow(namingContext
					.resolve(clientName));

			if (first_tag.equals("T") || second_tag.equals("T")) {
				clientUpdater.appendTemperatureClient(client);

			}
			if (first_tag.equals("P") || second_tag.equals("P")) {
				clientUpdater.appendPressureClient(client);

			}
			if (first_tag.equals("H") || second_tag.equals("H")) {
				clientUpdater.appendHumidityClient(client);
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

		return "["
				+ timeString + "] - <" + sensorName + ">   " + tagValues + measurementUnit ;
	}
}
