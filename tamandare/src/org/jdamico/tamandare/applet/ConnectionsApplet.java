package org.jdamico.tamandare.applet;

import java.awt.BorderLayout;
import java.util.Date;

import javax.swing.JApplet;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;

public class ConnectionsApplet extends JApplet implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4042080746059277515L;
	
	
	Thread clockThread;
    // Date will give us the current hours, minutes and seconds
    Date date;
    // This variable will remain true for as long
    // we want the thread to run.
    boolean running = true;
    
    String time = null;

	// This method is called once by the browser when it starts the applet.
	public void init() {   

		
		//getContentPane().setOpaque(true);
		
		
		clockThread= new Thread(this);
        clockThread.start(); 
        
        //tableBuild();
        repaint(1000);

	}

	public void destroy()
    {
         // will cause thread to stop looping
         running = false;
         // destroy it.
         clockThread = null;
    } 
	
	
	@Override
	public void run() {
		// loop until told to stop
        while (running)
        {
             // Construct the current date.
             date = new Date();
             // Get the hours, minutes and hours
             time = date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
             // Put that result in the textfield
             
             //Now the reason for threads
             try
             {
                   // Wait 500milliseconds before continuing
                  clockThread.sleep(1500);
                  tableBuild();
                  
             }
             catch (InterruptedException e)
             {
                  System.out.println(e);
              }
             // he has wait and will now restart his actions.
        } 
		
	}
	
	
	public void tableBuild(){
		String[][] cellData = {{time, "row1-col2"},{"row2-col1", "row2-col2"},{"row2-col1", "row2-col2"}};
		String[] columnNames = {"Col-1", "Col-2"};
		JTable table = new JTable(cellData, columnNames);
		table.setEnabled(false);
		// table.setVisible(true); NOT REQUIRED

		
		getContentPane().add(new JScrollPane(table),BorderLayout.CENTER);
		
	}
}
