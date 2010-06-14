package org.jdamico.tamandare.components;

import org.jdamico.tamandare.exceptions.TamandareException;
import org.jdamico.tamandare.socket.ComplexPacket;
import org.jdamico.tamandare.threads.ThreadRunnableManager;
import org.jdamico.tamandare.utils.Constants;
import org.jdamico.tamandare.utils.ManageProperties;
import org.jdamico.tamandare.utils.TamandareHelper;
import org.jdamico.tamandare.web.JettyController;

public class ComplexPacketCommandManager {
	private static ComplexPacketCommandManager INSTANCE = null;
	public static ComplexPacketCommandManager getInstance(){
		if(INSTANCE == null) INSTANCE = new ComplexPacketCommandManager();
		return INSTANCE;
	}
	
	public boolean areU(String value) throws TamandareException{
		
		LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "areU(String "+value+")");
		
		boolean ret = false;
		String whoami = ManageProperties.getInstance().read(Constants.WHO_AM_I).trim();
		ComplexPacket cp = TamandareHelper.getInstance().string2ComplexPacket(value);
		value = cp.getValue();
		
		boolean isEntityNameStored = EntityManager.getInstance().isEntityNameStored(value);
		
				
		
		if(value.equals(whoami) && isEntityNameStored) ret = true;

		return ret;
	}
	
	public String sendSignature(String sComplexPacket) throws TamandareException{

		LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "sendSignature(String "+sComplexPacket+")");
		
		ComplexPacket cp = TamandareHelper.getInstance().string2ComplexPacket(sComplexPacket);
		
		ThreadRunnableManager.getInstance().startSessionAcceptanceProcess("sendSessionAcceptance", cp.getFromAddr(), cp.getValue());

		return "WAIT";
	}
	
	public String sendSessionAcceptance(String sComplexPacket) throws TamandareException{

		LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "sendSessionAcceptance(String "+sComplexPacket+")");
		ComplexPacket cp = TamandareHelper.getInstance().string2ComplexPacket(sComplexPacket);
		LiveMemoryManager.setSessions(cp.getValue(), true);
		
		/*TODO:
		 * Start a new thread to request the remote tags intersection and its urls
		 */
		
		openRemoteRequest(); /* atomic */
		ThreadRunnableManager.getInstance().sendMyTags(cp.getFromAddr()); 	/* thread to send my tags */
		
		return "true";
	}
	
	public String sendMachineSessionAcceptance(String sComplexPacket) throws TamandareException{

		LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "sendMachineSessionAcceptance(String "+sComplexPacket+")");
		ComplexPacket cp = TamandareHelper.getInstance().string2ComplexPacket(sComplexPacket);
		LiveMemoryManager.setSessions(cp.getValue(), true);
		
		/*TODO:
		 * Start a new thread to request the remote tags intersection and its urls
		 */
		
		openRemoteRequest(); /* atomic */
		
		ThreadRunnableManager.getInstance().createPreSession(cp.getFromAddr());
		//ThreadRunnableManager.getInstance().sendMyTags(cp.getFromAddr()); 	/* thread to send my tags */
		//ThreadRunnableManager.getInstance().sendMySignatures(cp.getFromAddr()); 
		
		return "true";
	}
	
	public String sendSessionDenied(String sComplexPacket) throws TamandareException{

		LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "sendSessionDenied(String "+sComplexPacket+")");
		ComplexPacket cp = TamandareHelper.getInstance().string2ComplexPacket(sComplexPacket);
		LiveMemoryManager.setSessions(cp.getValue(), false);
		
		/* TODO:
		 * Consider to remove mapping for /RemoteRequest
		 */
		
		return "false";
	}
	
	public void openRemoteRequest(){
		JettyController.handler.addServletWithMapping("org.jdamico.tamandare.web.RemoteRequest",	"/RemoteRequest");
	}
	
	
	
}
