package org.jdamico.tamandare.threads;

import org.jdamico.tamandare.components.LiveMemoryManager;
import org.jdamico.tamandare.exceptions.TamandareException;
import org.jdamico.tamandare.socket.Client;
import org.jdamico.tamandare.socket.ComplexPacket;
import org.jdamico.tamandare.transactions.Derbymanager;
import org.jdamico.tamandare.utils.Constants;
import org.jdamico.tamandare.utils.ManageProperties;

public class SignatureThread  implements Runnable {

	private String threadName;
	private String host;
	private String entityName;
	
	public SignatureThread( String threadName, String host, String entityName ) {
		this.threadName = threadName; 
		this.host = host;
		this.entityName = entityName;
	}
	
	@Override
	public void run() {
		try 
		{	
			
			
			
			String signature = Derbymanager.getInstance().getSignature(entityName);

			
			LiveMemoryManager.getInstance().setSessions(host, false);
			
			Client  socketClient = new Client();
			ComplexPacket cp = new ComplexPacket(host, "sendSignature", signature, ManageProperties.getInstance().read(Constants.MY_ADDR)); 
			cp = socketClient.sendSignature(cp, entityName);
				
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
