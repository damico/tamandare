package org.jdamico.tamandare.threads;

import org.jdamico.tamandare.components.ConnectionManager;
import org.jdamico.tamandare.components.LoggerManager;

public class PrivateNetSyncThread implements Runnable {

	private String host =null;
	private String entityName;

	public PrivateNetSyncThread(String host, String entityName) {
		this.host = host;
		this.entityName = entityName;
	}
	@Override
	public void run() {
		try {
			ConnectionManager.getInstance().isHostValid(host, entityName);
		} catch (Exception e) {
			LoggerManager.getInstance().logAtExceptionTime(this.getClass().getName(), e.getMessage());
			e.printStackTrace();
		}
	}

}
