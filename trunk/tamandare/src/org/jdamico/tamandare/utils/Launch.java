package org.jdamico.tamandare.utils;

import java.io.IOException;

import org.jdamico.tamandare.socket.Server;
import org.jdamico.tamandare.web.JettyController;

public class Launch {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		Server si = new Server();
		//si.initServer();
		
		JettyController jController = new JettyController();
		jController.init();
		
		
System.out.println("init 0 done");
si.initServer();
System.out.println("init 1 done");
	}

}
