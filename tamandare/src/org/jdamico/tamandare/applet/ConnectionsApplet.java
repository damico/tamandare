package org.jdamico.tamandare.applet;

import java.awt.BorderLayout;

import javax.swing.JApplet;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ConnectionsApplet extends JApplet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4042080746059277515L;

	// This method is called once by the browser when it starts the applet.
	public void init() {   

		String[][] cellData = {{"row1-col1", "row1-col2"},{"row2-col1", "row2-col2"},{"row2-col1", "row2-col2"}};
		String[] columnNames = {"Col-1", "Col-2"};
		JTable table = new JTable(cellData, columnNames);
		table.setEnabled(false);
		// table.setVisible(true); NOT REQUIRED

		getContentPane().add(new JScrollPane(table),BorderLayout.CENTER);
		//getContentPane().setOpaque(true);

	}
}
