package org.jdamico.tamandare.threads;

import java.util.ArrayList;
import java.util.Date;

import org.jdamico.tamandare.components.LoggerManager;
import org.jdamico.tamandare.dataobjects.Job;
import org.jdamico.tamandare.transactions.TransactionManager;
import org.jdamico.tamandare.utils.TamandareHelper;


public class StartSchedulerMonitorThread extends Thread {

	

	public StartSchedulerMonitorThread() {}
	
	public void run() {
		
		
		try {
			while (true) {
				
				TransactionManager tm = new TransactionManager();
				ArrayList<Job> jobs = tm.getJobs();
				if(jobs!=null && jobs.size() > 0){
					for(int i=0; i<jobs.size(); i++){
						if(TamandareHelper.getInstance().isReady(new Date(), jobs.get(i).getTimestamp(), jobs.get(i).getInterval())){
							System.out.println("Go baby!");
							if(jobs.get(i).getThread().equals("privateNetSyncThread")){
								ThreadRunnableManager.getInstance().privateNetSyncThread();
								tm.updateKeepSync(new Date());
							}else if(jobs.get(i).getThread().equals("killJob")){
								tm.deleteJobByName("killJob");
								LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "Killing me softly...");
								System.exit(0);
							} 
						}
					}
				}
				
				System.out.println("Monitor loop!");
				Thread.sleep(30000);
			}
			
			
			
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
