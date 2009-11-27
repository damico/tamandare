package org.jdamico.tamandare.threads;

import java.util.ArrayList;

import org.jdamico.tamandare.socket.Client;
import org.jdamico.tamandare.socket.ComplexPacket;
import org.jdamico.tamandare.transactions.TransactionManager;
import org.jdamico.tamandare.utils.Constants;
import org.jdamico.tamandare.utils.ManageProperties;
import org.jdamico.tamandare.utils.TamandareHelper;

public class TagsHandShakeThread  implements Runnable {

	private String threadName;
	private String host;
	private String signature;
	
	public TagsHandShakeThread( String threadName, String host, String signature ) {
		this.threadName = threadName; 
		this.host = host;
		this.signature = signature;
	}
	
	@Override
	public void run() {
		try 
		{	
			
			
			TransactionManager tm = new TransactionManager();
			ArrayList<String> tagsArray = tm.getTags();
			StringBuffer sb =  new StringBuffer();
			for(int i = 0; i < tagsArray.size(); i++){
				sb.append(tagsArray.get(i)+" ");
			}
			
			//?tags=sb.toString();
			/*
			 * send mytags to get the intersection
			 * get the intersection, transform in a urlHash array
			 * for each element of array start a thread to get each document thru the urlHash
			 * if the hash is existent, and the tagsHash is different make a update of tags 
			 */
			boolean value = tm.isEntitySignatureStored(signature);
			
			Client  socketClient = new Client();
			
			ComplexPacket cp = new ComplexPacket(host, threadName, String.valueOf(value), ManageProperties.getInstance().read(Constants.MY_ADDR)); 
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
