package org.jdamico.tamandare.threads;

import org.jdamico.tamandare.components.LiveMemoryManager;
import org.jdamico.tamandare.exceptions.TamandareException;
import org.jdamico.tamandare.socket.Client;
import org.jdamico.tamandare.socket.ComplexPacket;
import org.jdamico.tamandare.transactions.Derbymanager;

public class SessionAcceptanceThread  implements Runnable {

	private String threadName;
	private String host;
	private String signature;
	
	public SessionAcceptanceThread( String threadName, String host, String signature ) {
		this.threadName = threadName; 
		this.host = host;
		this.signature = signature;
	}
	
	@Override
	public void run() {
		try 
		{	
			
			
			
			boolean value = Derbymanager.getInstance().isSignature(signature);
			
			Client  socketClient = new Client();
			
			ComplexPacket cp = new ComplexPacket(host, threadName, String.valueOf(value)); 
				cp = socketClient.sendSessionAcceptance(cp);

			
			
				
				//boolean ret = Boolean.valueOf(cp.getValue());
				//if(ret) combo = setSuccessXML("Host found and valid, connection initialized.");
				//else combo = setSuccessXML("Host found and valid, but unrecognized by you. ");
				
			
			
		} 
		
		catch ( Exception exception )
		{
			exception.printStackTrace();
		} 

		
		
	}

}
