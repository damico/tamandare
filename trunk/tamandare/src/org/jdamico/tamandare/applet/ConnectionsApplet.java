package org.jdamico.tamandare.applet;

import java.applet.Applet;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ConnectionsApplet extends Applet {
    /**
	 * 
	 */
	private static final long serialVersionUID = -4042080746059277515L;

	// This method is called once by the browser when it starts the applet.
    public void init() {
    	
    	setLayout(new GridLayout(1,0));
    	
    	
       	
        String[] columnNames = {"First Name",
                                "Last Name",
                                "Sport",
                                "# of Years",
                                "Vegetarian"};

        Object[][] data = {
            {"Mary", "Campione",
             "Snowboarding", new Integer(5), new Boolean(false)},
            {"Alison", "Huml",
             "Rowing", new Integer(3), new Boolean(true)},
            {"Kathy", "Walrath",
             "Knitting", new Integer(2), new Boolean(false)},
            {"Sharon", "Zakhour",
             "Speed reading", new Integer(20), new Boolean(true)},
            {"Philip", "Milne",
             "Pool", new Integer(10), new Boolean(false)}
        };

        final JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

        table.setBounds(0, 0, 700, 500);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 0, 700, 500);
        //Add the scroll pane to this panel.
        add(scrollPane);

    	
    }

    // This method is called whenever the page containing this applet is made visible.
    public void start() {
    }

    // This method is called whenever the page containing this applet is not visible.
    public void stop() {
    }

    // This method is called once when the browser destroys this applet.
    public void destroy() {
    }

    // This method is called whenever this applet needs to repaint itself.
    public void paint(Graphics g) {
    }
}
