package org.jdamico.tamandare.web;


import org.jdamico.tamandare.utils.Constants;
import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.bio.SocketConnector;
import org.mortbay.jetty.servlet.ServletHandler;

public class JettyController implements Runnable {

	private Server server = null;
	private Thread t = null;
	public static ServletHandler handler = new ServletHandler(); /* needs to be static to be used by ComplexPacketCommandManager.openRequest */
	
	public JettyController(){
		int port = (Constants.WEB_SERVER_PORT);
		server = new Server();
		Connector connector = new SocketConnector();
		connector.setPort(port);
		server.setConnectors(new Connector[] { connector });

		
		server.setHandler(handler);

		handler.addServletWithMapping("org.jdamico.tamandare.web.Home",	"/home");
		handler.addServletWithMapping("org.jdamico.tamandare.web.PostUrl",	"/postUrl");
		handler.addServletWithMapping("org.jdamico.tamandare.web.PostSearch",	"/postSearch");
		handler.addServletWithMapping("org.jdamico.tamandare.web.Delete",	"/d");
		handler.addServletWithMapping("org.jdamico.tamandare.web.Update",	"/u");
		handler.addServletWithMapping("org.jdamico.tamandare.web.Entity",	"/postEntity");
		handler.addServletWithMapping("org.jdamico.tamandare.web.ListEntity",	"/listEntity");
		handler.addServletWithMapping("org.jdamico.tamandare.web.Connection",	"/initConn");
		handler.addServletWithMapping("org.jdamico.tamandare.web.Status",	"/status");
		handler.addServletWithMapping("org.jdamico.tamandare.web.KeepSync",	"/keepSync");
		handler.addServletWithMapping("org.jdamico.tamandare.web.SetIP",	"/setIP");
		handler.addServletWithMapping("org.jdamico.tamandare.web.KillMe",	"/killMe");
	}
	
	

	public void init() {
		t = new Thread(this);
		t.start();
	}

	public int stopServer() {
		t = new Thread(this);
		t.interrupt();
		return 1;
	}

	public void run() {
		try {
			server.start();
			server.join();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
