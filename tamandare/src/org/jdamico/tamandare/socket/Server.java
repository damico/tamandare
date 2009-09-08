package org.jdamico.tamandare.socket;

import java.io.IOException;
import java.net.ServerSocket;

import org.jdamico.tamandare.utils.Constants;



public class Server {
	public void initServer() throws IOException {
		ServerSocket serverSocket = null;
        boolean listening = true;
        int port = Constants.SOCKET_SERVER_PORT;
        try {
        	
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.err.println("Could not listen on port: "+port+".");
            System.exit(-1);
        }

        while (listening)
	    new ServerThread(serverSocket.accept()).start();

        serverSocket.close();
	}
}
