/*
 * Created by JFormDesigner on Tue Jun 29 22:14:37 BRT 2010
 */

package org.br.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class ClientGUI extends JFrame {

	private static final long serialVersionUID = 8560706222926619704L;
	DefaultListModel model = new DefaultListModel();
	Timer m_timer;

	public ClientGUI(String name, String tag) {
		super(name + "(" + tag + ")");
		initComponents();
		
		
	}

	public synchronized void updateModel(ArrayList<String> valueArray) {

		final int modelSize = model.size();
		final int valueArraySize = valueArray.size();

		final int delta = valueArraySize - modelSize;

		if (delta > 0) {

			for (int i = 0; i < delta; i++) {

				final String value = valueArray.get(modelSize + i);

				model.add(modelSize + i, value);
			}

		}
	}

	private void initComponents() {
		dialogPane = new JPanel();
		contentPanel = new JPanel();
		scrollPane1 = new JScrollPane();
		list1 = new JList(model);

		// ======== this ========
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		// ======== dialogPane ========
		{
			dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));

			// JFormDesigner evaluation mark
			dialogPane
					.setBorder(new javax.swing.border.CompoundBorder(
							new javax.swing.border.TitledBorder(
									new javax.swing.border.EmptyBorder(0, 0, 0,
											0), " ",
									javax.swing.border.TitledBorder.CENTER,
									javax.swing.border.TitledBorder.BOTTOM,
									new java.awt.Font("Dialog",
											java.awt.Font.BOLD, 12),
									java.awt.Color.red), dialogPane.getBorder()));
			dialogPane
					.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
						public void propertyChange(
								java.beans.PropertyChangeEvent e) {
							if ("border".equals(e.getPropertyName()))
								throw new RuntimeException();
						}
					});

			dialogPane.setLayout(new BorderLayout());

			// ======== contentPanel ========
			{
				contentPanel.setLayout(new GridBagLayout());
				((GridBagLayout) contentPanel.getLayout()).columnWidths = new int[] {
						0, 335, 0 };
				((GridBagLayout) contentPanel.getLayout()).rowHeights = new int[] {
						0, 0, 198, 0 };
				((GridBagLayout) contentPanel.getLayout()).columnWeights = new double[] {
						0.0, 1.0, 1.0E-4 };
				((GridBagLayout) contentPanel.getLayout()).rowWeights = new double[] {
						0.0, 0.0, 1.0, 1.0E-4 };

				// ======== scrollPane1 ========
				{
					scrollPane1.setViewportView(list1);
				}
				contentPanel.add(scrollPane1, new GridBagConstraints(0, 0, 2,
						3, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
			}
			dialogPane.add(contentPanel, BorderLayout.CENTER);
		}
		contentPane.add(dialogPane, BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(getOwner());

	}

	private JPanel dialogPane;
	private JPanel contentPanel;
	private JScrollPane scrollPane1;
	private JList list1;

}
