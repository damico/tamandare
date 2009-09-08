package org.jdamico.tamandare.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadRunnableManager {
	public void startSingatureProcess(String threadName, String host, String entityName ){
		// create and name each runnable             
		
		SignatureThread thread = new SignatureThread(threadName, host, entityName);
		
		


		System.out.println( "Starting thread" );

		// create ExecutorService to manage threads                        
		ExecutorService threadExecutor = Executors.newFixedThreadPool( 1 );

		// start threads and place in runnable state   
		threadExecutor.execute( thread ); // start task1
		
		if(threadExecutor.isTerminated()){
			threadExecutor.shutdown(); // shutdown worker threads
			System.out.println("done... executor");
		}

		

		System.out.println( "Threads started, main ends\n" );
	}

	public void startSessionAcceptanceProcess(String threadName, String host, String signature) {
	
		SessionAcceptanceThread thread = new SessionAcceptanceThread(threadName, host, signature);
		
		


		System.out.println( "Starting thread" );

		// create ExecutorService to manage threads                        
		ExecutorService threadExecutor = Executors.newFixedThreadPool( 1 );

		// start threads and place in runnable state   
		threadExecutor.execute( thread ); // start task1
		
		if(threadExecutor.isTerminated()){
			threadExecutor.shutdown(); // shutdown worker threads
			System.out.println("done... executor");
		}

		

		System.out.println( "Threads started, main ends\n" );
		
	}
}
