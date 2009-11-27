package org.jdamico.tamandare.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jdamico.tamandare.components.LoggerManager;

import sun.awt.windows.ThemeReader;

public class ThreadRunnableManager {
	public void startSingatureProcess(String threadName, String host, String entityName ){
		// create and name each runnable             
		
		SignatureThread thread = new SignatureThread(threadName, host, entityName);
		
		


		LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "Starting thread: "+threadName );

		// create ExecutorService to manage threads                        
		ExecutorService threadExecutor = Executors.newFixedThreadPool( 1 );

		// start threads and place in runnable state   
		threadExecutor.execute( thread ); // start task1
		
		if(threadExecutor.isTerminated()){
			threadExecutor.shutdown(); // shutdown worker threads
			
		}

		

		LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "Stopping thread: "+threadName );
	}

	public void startSessionAcceptanceProcess(String threadName, String host, String signature) {
	
		LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "Starting thread: "+threadName );
		
		SessionAcceptanceThread thread = new SessionAcceptanceThread(threadName, host, signature);
		
		


				// create ExecutorService to manage threads                        
		ExecutorService threadExecutor = Executors.newFixedThreadPool( 1 );

		// start threads and place in runnable state   
		threadExecutor.execute( thread ); // start task1
		
		if(threadExecutor.isTerminated()){
			threadExecutor.shutdown(); // shutdown worker threads
			
		}

		

		LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "Stopping thread: "+threadName );
		
	}
}
