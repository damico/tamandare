package org.jdamico.tamandare.threads;


public class StartSchedulerMonitorThread extends Thread {

	

	public StartSchedulerMonitorThread() {}
	
	public void run() {
		
		
		try {
			while (true) {
				ThreadRunnableManager.getInstance().privateNetSyncThread();
				System.out.println("go!!!!!!!!!!!!");
				Thread.sleep(30000);
			}
			
			
			
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
