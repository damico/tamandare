package org.jdamico.tamandare.components;

import org.jdamico.tamandare.exceptions.TamandareException;
import org.jdamico.tamandare.socket.ComplexPacket;
import org.jdamico.tamandare.threads.ThreadRunnableManager;
import org.jdamico.tamandare.utils.Constants;
import org.jdamico.tamandare.utils.ManageProperties;
import org.jdamico.tamandare.utils.TamandareHelper;

public class ComplexPacketCommandManager {
	private static ComplexPacketCommandManager INSTANCE = null;
	public static ComplexPacketCommandManager getInstance(){
		if(INSTANCE == null) INSTANCE = new ComplexPacketCommandManager();
		return INSTANCE;
	}
	
	public boolean areU(String value){
		boolean ret = false;
		if(value.equals(ManageProperties.getInstance().read(Constants.WHO_AM_I))) ret = true;
		return ret;
	}
	
	public void sendSignature(String sComplexPacket) throws TamandareException{

		System.err.println("sendSignature(String "+sComplexPacket+")");
		
		ComplexPacket cp = TamandareHelper.getInstance().string2ComplexPacket(sComplexPacket);
		
		ThreadRunnableManager trm = new ThreadRunnableManager();
		trm.startSessionAcceptanceProcess("SessionAcceptance", cp.getAddr(), cp.getValue());

		
	}
	
	public void sendSessionAcceptance(String sComplexPacket) throws TamandareException{

		System.err.println("sendSessionAcceptance(String "+sComplexPacket+")");
		
		
	}
}
