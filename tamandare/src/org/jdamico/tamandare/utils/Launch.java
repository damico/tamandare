package org.jdamico.tamandare.utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.jdamico.tamandare.components.LoggerManager;
import org.jdamico.tamandare.dataobjects.NetworkInterfaceObject;
import org.jdamico.tamandare.socket.Server;
import org.jdamico.tamandare.threads.StartSchedulerMonitorThread;
import org.jdamico.tamandare.threads.ThreadRunnableManager;
import org.jdamico.tamandare.transactions.TransactionManager;
import org.jdamico.tamandare.web.JettyController;

public class Launch extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1277316240415521289L;
	private static JComboBox ipCB = null;
	private static JButton startAgent = new JButton();

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {



		TransactionManager tm = new TransactionManager();
		tm.checkDB();
		//createPreFrame();
		
		
		StartSchedulerMonitorThread ssmt = new StartSchedulerMonitorThread();
		ssmt.start();
		
		
		Server si = new Server(); //Socket Server
		//Scheduler Monitor Thread
		JettyController jController = new JettyController();
		jController.init(); //Jetty Server
		System.out.println("init 0 done");
		si.initServer();
		System.out.println("init 1 done");



	}

	private static void createPreFrame() throws Exception {

		String[] ips = TamandareHelper.getInstance().getMyIPsByStringArray();
		JFrame preFrame = new JFrame("Tamandare Agent: Network setup");
		preFrame.setSize(490, 60);
		JLabel ipsLabel = new JLabel();
		ipsLabel.setBounds(15, 10, 150, 20);
		ipsLabel.setText("Select the correct IP:");


		ipCB = new JComboBox(ips);

		ipCB.setBounds(160, 10, 150, 20);


		startAgent.setText("Start Agent");
		startAgent.setBounds(320, 10, 150, 20);
		startAgent.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				ManageProperties.getInstance().setProp(Constants.AGENT_NET_PATH, Constants.MY_ADDR, (String) ipCB.getSelectedItem());
				startAgent.setText("Agent started");
				startAgent.setEnabled(false);
				LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "MY_ADDR: "+ManageProperties.getInstance().read(Constants.AGENT_NET_PATH, Constants.MY_ADDR));
			}

		});

		preFrame.add(ipsLabel);
		preFrame.add(ipCB);
		preFrame.add(startAgent);

		preFrame.setLayout(null);

		preFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		preFrame.setResizable(false);
		preFrame.setVisible(true);
	}

}
