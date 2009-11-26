package org.jdamico.tamandare.components;

import java.util.HashMap;
import java.util.Map;

public class LiveMemoryManager {
	
	private static Map<String, Boolean> sessions = new HashMap<String, Boolean>();
	
	private static LiveMemoryManager INSTANCE = null;
	public static LiveMemoryManager getInstance(){
		if(INSTANCE == null) INSTANCE = new LiveMemoryManager();
		return INSTANCE;
	}
	public static Map<String, Boolean> getSessions() {
		LoggerManager.getInstance().logAtDebugTime(LiveMemoryManager.class.getClass().getName(), "getSessions()");
		
		return sessions;
	}
	public static void setSessions(String host, boolean status) {
		LoggerManager.getInstance().logAtDebugTime(LiveMemoryManager.class.getClass().getName(), "setSessions(String host, boolean status)");
		sessions.put(host, status);
	}
	
	
}
