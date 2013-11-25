package org.br.gui;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import org.br.util.Util;

public class SensorGUI extends JFrame {

	private static final long serialVersionUID = 4728590686582858618L;

	private JLabel valueLabel;
	private JButton shutDownButton;
	private Container container;
	private JPanel panel;

	private String name;
	private String tag;

	public SensorGUI(String name, String tag) {
		super("sensors_reading_DS");

		this.name = name;
		this.tag = tag;

		initComponents();
	}

	public void setValueLabel(String value) {

		final String measurementUnit = Util.getMeasurementUnit(tag);

		this.valueLabel.setText(value + measurementUnit);
	}

	private void initComponents() {

		panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Sensor - " + name));

		shutDownButton = new JButton("Shut Down");
		valueLabel = new JLabel("0");
		container = new Container();
		container.setLayout(new GridLayout(0, 2));
		container.add(new JLabel("Value (" + tag + "):"));
		container.add(valueLabel);
		container.add(new JLabel(""));
		container.add(new JLabel(""));
		container.add(shutDownButton);

		GroupLayout jPanel1Layout = new GroupLayout(panel);
		panel.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup()
				.addGroup(
						jPanel1Layout
								.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										jPanel1Layout.createParallelGroup()
												.addComponent(container))
								.addGap(20).addContainerGap()));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup()
				.addGroup(
						jPanel1Layout
								.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										jPanel1Layout.createParallelGroup()
												.addComponent(container))
								.addGap(20).addContainerGap()));

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup().addGroup(
				layout.createSequentialGroup().addContainerGap().addGap(20)
						.addComponent(panel).addGap(20).addContainerGap()));

		layout.setVerticalGroup(layout.createParallelGroup().addGroup(
				layout.createSequentialGroup()
						.addContainerGap()
						.addGap(20)
						.addGroup(
								layout.createParallelGroup()
										.addComponent(panel)).addGap(15)
						.addContainerGap()));

		pack();

		shutDownButton.addMouseListener(new MouseAdapter() {
			// public void mouseClicked(MouseEvent evt) {
			// falharMouseClicked(evt);
			// }
		});

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}
