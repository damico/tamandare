package org.jdamico.tamandare.web;


import org.jdamico.tamandare.utils.Constants;
import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.bio.SocketConnector;
import org.mortbay.jetty.servlet.ServletHandler;

public class JettyController implements Runnable {

	private Server server = null;
	private Thread t = null;
	
	public JettyController(){
		int port = (Constants.WEB_SERVER_PORT);
		server = new Server();
		Connector connector = new SocketConnector();
		connector.setPort(port);
		server.setConnectors(new Connector[] { connector });

		ServletHandler handler = new ServletHandler();
		server.setHandler(handler);

		handler.addServletWithMapping("org.jdamico.tamandare.web.Home",	"/home");
		handler.addServletWithMapping("org.jdamico.tamandare.web.PostUrl",	"/postUrl");
		handler.addServletWithMapping("org.jdamico.tamandare.web.PostSearch",	"/postSearch");
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
