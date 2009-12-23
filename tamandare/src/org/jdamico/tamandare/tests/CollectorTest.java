package org.jdamico.tamandare.tests;

import java.util.ArrayList;

import org.jdamico.tamandare.exceptions.TamandareException;
import org.jdamico.tamandare.transactions.PostgreSQLmanager;
import org.jdamico.tamandare.urlreader.Collector;
import org.jdamico.tamandare.urlreader.ThreadRunnableManager;
import org.jdamico.tamandare.utils.Constants;

import junit.framework.TestCase;

public class CollectorTest extends TestCase {
	private Collector collector = new Collector();
	public void testOne() throws TamandareException {
		
		
		/* TODO:
		 * 0) Notify that link was read
		 * 1) Add get title thread
		 * 2) Add get meta thread
		 * 3) Add get content thread
		 * 4) Add get inner urls threads
		 */
		
		ArrayList<String> urlArray = PostgreSQLmanager.getInstance().getUrlArrayByStatus(Constants.HTML_RAW);
		
		ThreadRunnableManager trm = new ThreadRunnableManager();
		trm.startGetUrlContent();		


		int max = 0;
		while(max <5){
			System.err.println("initializing...");
			urlArray = PostgreSQLmanager.getInstance().getUrlArrayByStatus(Constants.HTML_RAW);

			for(int i = 0; i < urlArray.size(); i++){
				String htmlContent = null;
				try{
					htmlContent = collector.getUrlText(urlArray.get(i));
				}catch(Exception e){}
				

				if(htmlContent!=null){
					/* paralell stuff */
					trm.startGetTitleThread(urlArray.get(i), htmlContent); 
					//trm.startGetMetaContentThread(urlArray.get(i), htmlContent);
					//trm.startGetInnerUrls(urlArray.get(i), htmlContent);
				}
				
				
				
				
				
			}
			
			System.err.println("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH" + max);
			
			max++;
		}
		
		


		
	}

	
	
	
}
