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
	
	public boolean areU(String value) throws TamandareException{
		
		LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "areU(String value)");
		
		boolean ret = false;
		String whoami = ManageProperties.getInstance().read(Constants.WHO_AM_I).trim();
		ComplexPacket cp = TamandareHelper.getInstance().string2ComplexPacket(value);
		value = cp.getValue();
		if(value.equals(whoami)) ret = true;

		return ret;
	}
	
	public void sendSignature(String sComplexPacket) throws TamandareException{

		LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "sendSignature(String "+sComplexPacket+")");
		
		ComplexPacket cp = TamandareHelper.getInstance().string2ComplexPacket(sComplexPacket);
		
		ThreadRunnableManager trm = new ThreadRunnableManager();
		trm.startSessionAcceptanceProcess("SessionAcceptance", cp.getAddr(), cp.getValue());

		
	}
	
	public void sendSessionAcceptance(String sComplexPacket) throws TamandareException{

		LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "sendSessionAcceptance(String "+sComplexPacket+")");
		ComplexPacket cp = TamandareHelper.getInstance().string2ComplexPacket(sComplexPacket);
		LiveMemoryManager.setSessions(cp.getValue(), true);
		
		
	}
}
