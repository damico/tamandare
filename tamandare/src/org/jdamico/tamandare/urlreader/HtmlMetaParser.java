package org.jdamico.tamandare.urlreader;

import java.io.UnsupportedEncodingException;

import org.jdamico.tamandare.exceptions.TamandareException;
import org.jdamico.tamandare.transactions.PostgreSQLmanager;
import org.jdamico.tamandare.utils.Constants;


public class HtmlMetaParser implements Runnable {

	private String urlStr;
	private String htmlContent;
	
	public HtmlMetaParser(String urlStr, String htmlContent) {
		this.urlStr = urlStr;
		this.htmlContent = htmlContent;
	}

	@Override
	public void run() {
		HtmlParser data = new HtmlParser();
		
		try {
			PostgreSQLmanager.getInstance().updateUrl(urlStr, Constants.THREAD_BUSY);
			String content = data.getMeta(htmlContent);
			System.err.println(content);
			PostgreSQLmanager.getInstance().updateUrl(urlStr, content, Constants.HTML_TITLE);
		} catch (TamandareException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
				
		
	}

	

}
