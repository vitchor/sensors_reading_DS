package org.br.servant;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.br.corbaSupport.service.ServicePOA;
import org.br.gui.ServiceGUI;
import org.br.util.Buffer;
import org.br.util.Util;

public class ServiceServant extends ServicePOA {

	private ServiceGUI serviceGUI;

	private Buffer temperatureBuffer;
	private Buffer pressureBuffer;
	private Buffer humidityBuffer;

	public ServiceServant() {
		super();

		temperatureBuffer = new Buffer();

		this.serviceGUI = new ServiceGUI();
		this.serviceGUI.setVisible(true);
	}

	@Override
	public int updateSensorValues(String sensorName, String tag,
			String tagValues) {

		final String measurementUnit = Util.getMeasurementUnit(tag);
		final SimpleDateFormat timeFormat = new SimpleDateFormat(
				"dd/MM/yyyy HH:mm:ss");
		final String timeString = timeFormat.format(new Date());

		final String valueFinalFormat = "<" + sensorName + ">   " + tagValues
				+ measurementUnit + "   [" + timeString + "]";

		if (tag.equals("T")) {
			temperatureBuffer.append(valueFinalFormat);
		} else if (tag.equals("P")) {
			pressureBuffer.append(valueFinalFormat);
		} else if (tag.equals("H")) {
			humidityBuffer.append(valueFinalFormat);
		} else {
			return 0;
		}

		serviceGUI.setclientLabelText(valueFinalFormat);

		return 1;
	}

	@Override
	public int registerClient(String client_name) {
		// TODO Auto-generated method stub
		return 0;
	}
}
