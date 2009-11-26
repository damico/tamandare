package org.jdamico.tamandare.components;

import java.io.IOException;

import org.jdamico.tamandare.dataobjects.Combo;
import org.jdamico.tamandare.exceptions.TamandareException;
import org.jdamico.tamandare.socket.Client;
import org.jdamico.tamandare.socket.ComplexPacket;
import org.jdamico.tamandare.threads.ThreadRunnableManager;

public class ConnectionManager extends TamandareObjectManager {
	private static ConnectionManager INSTANCE = null;
	public static ConnectionManager getInstance(){
		if(INSTANCE == null) INSTANCE = new ConnectionManager();
		return INSTANCE;
	}
	
	public Combo isHostValid(String host, String entityName) throws IOException {
		LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "isHostValid(String host, String entityName)");
		
		Combo combo = new Combo();
		
		Client  socketClient = new Client();
		String socketReply = "-";
		try {
			socketReply = socketClient.initClient("?", host);
			
		} catch (TamandareException e) {
			combo = setErrorXML(e, combo);
			e.printStackTrace();
		}
		if(socketReply.equals("!")){
			
			combo = areU(host, entityName);
			sendSignature(host, entityName);
			
		}else{
			combo = setSuccessXML("Invalid host.");
		}
		
		return combo;
	}
	
	public Combo areU(String host, String entityName) throws IOException {
		LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "areU(String host, String entityName)");
		
		Combo combo = new Combo();
		
		Client  socketClient = new Client();
		ComplexPacket cp = new ComplexPacket(host, "areU", entityName); 

		try {
			cp = socketClient.areU(cp, entityName);
			
			boolean ret = Boolean.valueOf(cp.getValue());
			if(ret) combo = setSuccessXML("Host found and valid, connection initialized.");
			else combo = setSuccessXML("Host found and valid, but unrecognized by you ("+host+": I am not '"+entityName+"'). ");
			
		
		} catch (TamandareException e) {
			combo = setErrorXML(e, combo);
			e.printStackTrace();
		}
		
		
		return combo;
	}
	
	
	public void sendSignature(String host, String entityName) throws IOException {
		LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "sendSignature(String host, String entityName)");
		
			ThreadRunnableManager trm = new ThreadRunnableManager();
			trm.startSingatureProcess("sendSignature", host, entityName);
		
	}
}
