package org.jdamico.tamandare.urlreader;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.jdamico.tamandare.utils.Constants;

public class ThreadRunnableManager {
	
	public void startGetUrlContent(){
		CollectorThread collectorThread = new CollectorThread();
		ExecutorService threadExecutor = Executors.newFixedThreadPool( 1 );
		threadExecutor.execute(collectorThread);
//		try {
//			int time =75;
//			threadExecutor.awaitTermination(time,  TimeUnit.SECONDS);
//			startTimeCounter(time);
//		} catch (InterruptedException e) {
//			System.err.println("###################################################################");
//		}
		
		if(threadExecutor.isTerminated()){
			threadExecutor.shutdown(); // shutdown worker threads
			
		}
	}

	public void startTimeCounter(int time) {
		Timer timer = new Timer(time);
		ExecutorService threadExecutor = Executors.newFixedThreadPool( 1 );
		threadExecutor.execute(timer);
	}

	public void startGetTitleThread(String link, String htmlContent) {
		ExecutorService threadExecutor = Executors.newFixedThreadPool( 3 );
		threadExecutor.execute(UrlThreadFactory.getThread(Constants.HTML_TITLE, link, htmlContent));
		threadExecutor.execute(UrlThreadFactory.getThread(Constants.HTML_META, link, htmlContent));
		threadExecutor.execute(UrlThreadFactory.getThread(Constants.HTML_INNER_URLS, link, htmlContent));
	}

	public void startGetMetaContentThread(String link, String htmlContent) {
		ExecutorService threadExecutor = Executors.newFixedThreadPool( 1 );
		threadExecutor.execute(UrlThreadFactory.getThread(Constants.HTML_META, link, htmlContent));
		
	}

	public void startGetInnerUrls(String link, String htmlContent) {
		ExecutorService threadExecutor = Executors.newFixedThreadPool( 1 );
		threadExecutor.execute(UrlThreadFactory.getThread(Constants.HTML_INNER_URLS, link, htmlContent));
		
	}
}
