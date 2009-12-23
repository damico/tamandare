package org.jdamico.tamandare.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jdamico.tamandare.components.LoggerManager;

public class ThreadRunnableManager {
	
	
	private static ThreadRunnableManager INSTANCE = null;
	public static ThreadRunnableManager getInstance(){
		if(INSTANCE == null) INSTANCE = new ThreadRunnableManager();
		return INSTANCE;
	}
	
	
	public void startSingatureProcess(String threadName, String host, String entityName ){
		// create and name each runnable             
		
		SignatureThread thread = new SignatureThread(threadName, host, entityName);
		
		


		LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "Starting thread: "+threadName );

		ExecutorService threadExecutor = Executors.newFixedThreadPool( 1 );
		threadExecutor.execute( thread ); 
		if(threadExecutor.isTerminated()) threadExecutor.shutdown(); 
		LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "Stopping thread: "+threadName );
	}

	public void startSessionAcceptanceProcess(String threadName, String host, String signature) {
	
		LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "Starting thread: "+threadName );
		
		SessionAcceptanceThread thread = new SessionAcceptanceThread(threadName, host, signature);
		
		


		ExecutorService threadExecutor = Executors.newFixedThreadPool( 1 );
		threadExecutor.execute( thread ); 
		if(threadExecutor.isTerminated()) threadExecutor.shutdown(); 
		LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "Stopping thread: "+threadName );
		
	}


	public void sendMyTags(String remotePeer) {
		SendMyTagsThread thread = new SendMyTagsThread(remotePeer);
		String threadName = "sendMyTags";
		LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "Starting thread: "+threadName);
		
		ExecutorService threadExecutor = Executors.newFixedThreadPool( 1 );
		threadExecutor.execute( thread ); 
		if(threadExecutor.isTerminated()) threadExecutor.shutdown(); 
		LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "Stopping thread: "+threadName );
		
	}
	
	
}
