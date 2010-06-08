package org.jdamico.tamandare.threads;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
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


	public void sendMyDocsThread(String remotePeer, Map<String, String> docsMap) {
		
		
		int executorsNumber = docsMap.size();
		
		String threadName = "sendMyDocs";
		ExecutorService threadExecutor = Executors.newFixedThreadPool( executorsNumber );
		
		Collection<String> docsCol = docsMap.values();
		Iterator<String> iter = docsCol.iterator();
		int innerThread = 0;
		while(iter.hasNext()){
			threadExecutor.execute( new SendMyDocsThread(remotePeer, iter.next()));
			LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "Starting thread: "+threadName+" "+innerThread );
			if(threadExecutor.isTerminated()) threadExecutor.shutdown(); 
			LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "Stopping thread: "+threadName+" "+innerThread );
			innerThread++;
		}
		
 
		
	
		
	}
	
	
}
