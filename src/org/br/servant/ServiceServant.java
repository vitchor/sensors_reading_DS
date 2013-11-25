package org.br.servant;

import org.br.corbaSupport.service.ServicePOA;
import org.br.gui.ServiceGUI;

public class ServiceServant extends ServicePOA {

	ServiceGUI serviceGUI;
	
	public ServiceServant() {
		super();
		
		this.serviceGUI = new ServiceGUI();
		this.serviceGUI.setVisible(true);
	}
	
	@Override
	public int updateSensorValues(String sensor_name, String tag_name,
			String tag_values) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int registerClient(String client_name) {
		// TODO Auto-generated method stub
		return 0;
	}

}
