package org.jdamico.tamandare.threads;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jdamico.tamandare.components.LoggerManager;
import org.jdamico.tamandare.transactions.Derbymanager;

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
		if(threadExecutor.isTerminated()){
			threadExecutor.shutdown(); 
			LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "Stopping thread: "+threadName );
		}
	}

	public void startSessionAcceptanceProcess(String threadName, String host, String signature) {

		LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "Starting thread: "+threadName );

		SessionAcceptanceThread thread = new SessionAcceptanceThread(threadName, host, signature);

		ExecutorService threadExecutor = Executors.newFixedThreadPool( 1 );
		threadExecutor.execute( thread ); 
		if(threadExecutor.isTerminated()){
			threadExecutor.shutdown(); 
			LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "Stopping thread: "+threadName );
		}
	}


	public void sendMyTags(String remotePeer) {
		SendMyTagsThread thread = new SendMyTagsThread(remotePeer);
		String threadName = "sendMyTags";
		LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "Starting thread: "+threadName);

		ExecutorService threadExecutor = Executors.newFixedThreadPool( 1 );
		threadExecutor.execute( thread ); 
		if(threadExecutor.isTerminated()){
			threadExecutor.shutdown(); 
			LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "Stopping thread: "+threadName );
		}


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
			if(threadExecutor.isTerminated()){
				threadExecutor.shutdown(); 
				LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "Stopping thread: "+threadName+" "+innerThread );
			}

			innerThread++;
		}

	}


	public void sendIntersectionTagsThread(String remotePeer, ArrayList<String> intersectionTagsArray) {
		// request back_docs by passing intersection tags to RemoteRequest
		
		int executorsNumber = intersectionTagsArray.size();

		String threadName = "sendIntersectionTags";
		ExecutorService threadExecutor = Executors.newFixedThreadPool( executorsNumber );
		
		
		for(int i=0; i<intersectionTagsArray.size(); i++){
			
			
			threadExecutor.execute( new SendIntersectionTagsThread(remotePeer, intersectionTagsArray.get(i)));
			LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "Starting thread: "+threadName+" "+i );
			if(threadExecutor.isTerminated()){
				threadExecutor.shutdown(); 
				LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "Stopping thread: "+threadName+" "+i );
			}

			
		}
		
	}


	public void postDocsByTagThread(String remotePeer,	Map<String, String> backDocsMap) {
		
		int executorsNumber = backDocsMap.size();

		String threadName = "postDocsByTag";
		ExecutorService threadExecutor = Executors.newFixedThreadPool( executorsNumber );
		int innerThread = 0;
		
		Collection<String> col = backDocsMap.values();
		Iterator<String> iter = col.iterator();
		while(iter.hasNext()){
			threadExecutor.execute( new SendMyDocsThread(remotePeer, iter.next()));
			LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "Starting thread: "+threadName+" "+innerThread );
			if(threadExecutor.isTerminated()){
				threadExecutor.shutdown(); 
				LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "Stopping thread: "+threadName+" "+innerThread );
			}

			innerThread++;
		}
		
	}


}
