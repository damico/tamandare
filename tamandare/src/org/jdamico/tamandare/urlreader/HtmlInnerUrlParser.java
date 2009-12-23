package org.jdamico.tamandare.urlreader;

import org.jdamico.tamandare.exceptions.TamandareException;


public class HtmlInnerUrlParser implements Runnable {

	private String urlStr;
	private String htmlContent;
	
	public HtmlInnerUrlParser(String urlStr, String htmlContent) {
		this.urlStr = urlStr;
		this.htmlContent = htmlContent;
	}

	@Override
	public void run() {
		Collector collector = new Collector();
		try {
			collector.getInnerUrls(htmlContent, urlStr);
		} catch (TamandareException e) {
			e.printStackTrace();
		}	
	}

	

}
