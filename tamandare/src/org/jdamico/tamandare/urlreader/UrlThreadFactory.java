package org.jdamico.tamandare.urlreader;

import org.jdamico.tamandare.utils.Constants;



public class UrlThreadFactory {
	public static Runnable getThread(int type, String urlStr, String htmlContent){
		switch(type){
		case Constants.HTML_TITLE:
			return new HtmlTitleParser(urlStr, htmlContent);		
		case Constants.HTML_META:
			return new HtmlMetaParser(urlStr, htmlContent);
		case Constants.HTML_INNER_URLS:
			return new HtmlInnerUrlParser(urlStr, htmlContent);
		default:
			return null;
		}
	}
}
