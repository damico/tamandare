package org.jdamico.tamandare.urlreader;

import java.util.ArrayList;

import org.jdamico.tamandare.exceptions.TamandareException;
import org.jdamico.tamandare.transactions.PostgreSQLmanager;
import org.jdamico.tamandare.utils.Constants;

public class CollectorThread implements Runnable {

	private Collector collector = new Collector();



	@Override
	public void run() {
		
		
		
		try {
			
			ArrayList<String> urlArray = PostgreSQLmanager.getInstance().getUrlArrayByStatus(Constants.HTML_RAW);

			int max = 0;
			while(max <5){
				System.err.println("initializing...");
				 urlArray = PostgreSQLmanager.getInstance().getUrlArrayByStatus(Constants.HTML_RAW);

				System.err.println("urlArray: "+urlArray.size());

				for(int i = 0; i < urlArray.size(); i++){
					
					String htmlContent = null;
					try{
						htmlContent = collector.getUrlText(urlArray.get(i));
					}catch(Exception e){}
					

					if(htmlContent!=null){
						/* paralell stuff */
						//trm.startGetTitleThread(urlArray.get(i), htmlContent); 
						//trm.startGetMetaContentThread(urlArray.get(i), htmlContent);
						//trm.startGetInnerUrls(urlArray.get(i), htmlContent);
					}

				}
				
				System.err.println("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH" + max);
				
				max++;
			}

		
		} catch (TamandareException e) {
			e.printStackTrace();
		} 

	}

}
