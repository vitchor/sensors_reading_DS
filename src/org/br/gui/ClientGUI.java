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
import javax.swing.border.EmptyBorder;

public class ClientGUI extends JFrame {

	private static final long serialVersionUID = 8560706222926619704L;
	DefaultListModel model = new DefaultListModel();

	public ClientGUI() {
		initComponents();
	}

	public synchronized void updateModel(ArrayList<String> model) {
		this.model.clear();

		int index = 0;

		for (String string : model) {

			model.add(index, string);
			index = index + 1;
		}
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - victor asdasd
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
											0), "JFormDesigner Evaluation",
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
		// JFormDesigner - End of component initialization
		// //GEN-END:initComponents

		// m_timer = new Timer(m_interval, new TimerAction());
		// m_timer.start();

	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - victor asdasd
	private JPanel dialogPane;
	private JPanel contentPanel;
	private JScrollPane scrollPane1;
	private JList list1;
	// JFormDesigner - End of variables declaration //GEN-END:variables
	// private int m_interval = 2000; // Milliseconds between updates.
	// private Timer m_timer; // Timer fires to anmimate one step.

	// class TimerAction implements ActionListener {
	//
	// // ================================================== actionPerformed
	// public void actionPerformed(ActionEvent e) {
	// //URLReader cardapioGetter = new URLReader();
	// String cardapio = cardapioGetter.getCardapio();
	// char[] letras = cardapio.toCharArray();
	// String linhas[] = {};
	// int listIndex = 0;
	// int listDataIndex = 0;
	// int lastCharIndex=-1;
	// String linha;
	// model.clear();
	// for(int i =0;i<letras.length;i++ ){
	// if (letras[i]=='%'){
	// linha="";
	// for(int i2 = lastCharIndex+1;i2<i;i2++){
	// linha = linha + letras[i2];
	// }
	// lastCharIndex=i;
	// model.add(listIndex,linha);
	// listIndex++;
	// }
	// }
	//
	// }
	//
	// }
}
