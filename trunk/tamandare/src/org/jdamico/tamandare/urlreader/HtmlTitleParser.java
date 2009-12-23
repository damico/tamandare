package org.jdamico.tamandare.urlreader;

import org.jdamico.tamandare.exceptions.TamandareException;
import org.jdamico.tamandare.transactions.PostgreSQLmanager;
import org.jdamico.tamandare.utils.Constants;



public class HtmlTitleParser implements Runnable {

	private HtmlParser data = new HtmlParser();
	private String urlStr=null;
	private String htmlContent=null;
	
	public HtmlTitleParser(String urlStr, String htmlContent) {
		this.urlStr = urlStr;
		this.htmlContent = htmlContent;
	}

	public void run() {
		
		try {
			PostgreSQLmanager.getInstance().updateUrl(urlStr, Constants.THREAD_BUSY);
			String content = data.getTitle(urlStr, htmlContent);
			PostgreSQLmanager.getInstance().updateUrl(urlStr, content, Constants.HTML_TITLE);
		} catch (Exception e1) {
			try {
				throw new TamandareException(e1.getStackTrace());
			} catch (TamandareException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
