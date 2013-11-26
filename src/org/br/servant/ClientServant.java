package org.br.servant;

import java.util.ArrayList;

import org.br.corbaSupport.client.ClientPOA;
import org.br.gui.ClientGUI;
import org.omg.CORBA.Any;

public class ClientServant extends ClientPOA {

	String name;

	private String firstTag;
	private String secondTag;

	private ClientGUI firstGUI;
	private ClientGUI secondGUI;

	public ClientServant(String name, String firstTag, String secondTag) {
		super();
		this.name = name;
		this.firstTag = firstTag;
		this.secondTag = secondTag;

		firstGUI = new ClientGUI();
		firstGUI.setVisible(true);
		
		secondGUI = new ClientGUI();
		secondGUI.setVisible(true);

	}

	@Override
	public synchronized int updateTagValues(String tag_name, Any tag_values) {

		@SuppressWarnings("unchecked")
		ArrayList<String> tagValues = (ArrayList<String>) tag_values
				.extract_Value();

		System.out.println(tagValues.size());
		
		if (tag_name.equals(firstTag)) {
			firstGUI.updateModel(tagValues);

		} else if (tag_name.equals(secondTag)) {
			secondGUI.updateModel(tagValues);
		}

		return 0;
	}

}
