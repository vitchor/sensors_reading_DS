package org.br.servant;

import java.util.ArrayList;

import org.br.corbaSupport.client.ClientPOA;
import org.br.gui.ClientGUI;

public class ClientServant extends ClientPOA {

	private String name;

	private String firstTag;
	private String secondTag;

	private ArrayList<String> firstTagArrayList;
	private ArrayList<String> secondTagArrayList;

	private ClientGUI gui;

	public ClientServant(String name, String firstTag, String secondTag) {
		super();
		this.name = name;
		this.firstTag = firstTag;
		this.secondTag = secondTag;

		gui = new ClientGUI();
		gui.setVisible(true);

		firstTagArrayList = new ArrayList<String>();
		secondTagArrayList = new ArrayList<String>();
	}

	@Override
	public synchronized int updateTagValues(String tag_name, String tag_values) {

		if (tag_name.equals(firstTag)) {
			firstTagArrayList.add(tag_values);
			gui.updateModel(firstTagArrayList);

		} else if (tag_name.equals(secondTag)) {
			secondTagArrayList.add(tag_values);
			gui.updateModel(secondTagArrayList);
		}

		return 0;
	}
}
