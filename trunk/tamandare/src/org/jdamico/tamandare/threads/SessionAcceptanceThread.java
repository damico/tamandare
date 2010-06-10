package org.jdamico.tamandare.threads;

import org.jdamico.tamandare.components.ComplexPacketCommandManager;
import org.jdamico.tamandare.components.LoggerManager;
import org.jdamico.tamandare.socket.Client;
import org.jdamico.tamandare.socket.ComplexPacket;
import org.jdamico.tamandare.transactions.TransactionManager;
import org.jdamico.tamandare.utils.Constants;
import org.jdamico.tamandare.utils.ManageProperties;

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
			
			
			TransactionManager tm = new TransactionManager();
			boolean value = tm.isEntitySignatureStored(signature);
			
			LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "tm.isEntitySignatureStored("+signature+"): "+value);
			
			Client  socketClient = new Client();
			
			ComplexPacket cp = new ComplexPacket(host, threadName, host, ManageProperties.getInstance().read(Constants.AGENT_NET_PATH, Constants.MY_ADDR)); 
				cp = socketClient.sendSessionAcceptance(cp);

				if(Boolean.valueOf(cp.getValue())){
					ComplexPacketCommandManager.getInstance().openRemoteRequest();
				}
			
				
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
