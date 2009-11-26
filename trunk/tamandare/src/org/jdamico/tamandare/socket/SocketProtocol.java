package org.jdamico.tamandare.socket;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.jdamico.tamandare.components.ComplexPacketCommandManager;
import org.jdamico.tamandare.components.LoggerManager;
import org.jdamico.tamandare.exceptions.TamandareException;
import org.jdamico.tamandare.utils.TamandareHelper;



public class SocketProtocol {
	private static final int WAITING = 0;
    private static final int OPEN = 1;
    private int state = WAITING;

    public String processInput(String theInput) throws TamandareException, SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        String theOutput = null;
        if (state == WAITING) {
            theOutput = ":";
            state = OPEN;
        } else {
        	ProtocolResponse pr = parseMessage(theInput);
        	state = pr.getState();
        	theOutput = pr.getOutputMessage();
        } 
        
       
        
        return theOutput;
    }

    public ProtocolResponse parseMessage(String msg) throws TamandareException, SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException{
    	ProtocolResponse response = null;
    	

    	LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), msg);
    	
    	if(msg.length() == 1){
    		if(msg.equals("?")) response = new ProtocolResponse("!", OPEN);
    		if(msg.equals("!")) response = new ProtocolResponse("?", OPEN);
    	}else if(msg.length() > 1){
    		if(msg.charAt(0) == '{') response = new ProtocolResponse(executeComplexPacketCmd(TamandareHelper.getInstance().string2ComplexPacket(msg)), OPEN);
    	}
    	
    	
    	return response;
    }
    
    
    public ComplexPacket executeComplexPacketCmd(ComplexPacket complexPacket) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
    	
    	
    	
    	
    	Method cmdMethod = ComplexPacketCommandManager.getInstance().getClass().getMethod(complexPacket.getType(), new Class[]{String.class});
    	String value =  String.valueOf(cmdMethod.invoke(ComplexPacketCommandManager.getInstance(), complexPacket.getSComplexPacket()));
    	
    	complexPacket.setValue(value);
    	
    	return complexPacket;
    }
    
    
    


}
