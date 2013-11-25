package org.br.util;

import java.util.ArrayList;

public class Buffer {

	private ArrayList<String> array;

	public Buffer() {
		super();
		array = new ArrayList<String>();
	}

	public void append(String value) {
		array.add(value);
	}

	public void clear() {
		array.clear();
	}
}
