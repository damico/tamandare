package org.jdamico.tamandare.web;

import org.jdamico.tamandare.utils.Constants;
import org.jdamico.tamandare.utils.ManageProperties;

public class ServletUtils {
	private static ServletUtils INSTANCE = null;
	public static ServletUtils getInstance(){
		if(INSTANCE == null){
			INSTANCE = new ServletUtils();
		}
		return INSTANCE;
	}
	
	public String jsRedirect(String to){
		String ret = "<SCRIPT LANGUAGE=\"JavaScript\">window.location=\""+to+"\";</script>";
		return ret;
	}
	
	
	public String cleanTags(String tags){
		String ret = tags.replaceAll("<tags>", "");
		ret = ret.replaceAll("</tags>", " ");
		return ret;
	}
	
	public String getHTMLalert(String msg){
		String ret = 	"<table width='400' bgcolor = '#CCFFFF' border='0' align='center' cellpadding='8' cellspacing='8'>" +
						"<tr valign='top'><td> "+msg+" </td></tr>" +
						"</table>";
		return ret;
	}
	
	public String getHTMLHeader(String title){
		String ret = 	"<html><head><title>"+title+"</title></head><body>\n" +
						"<h1>"+title+" @ "+ManageProperties.getInstance().read(Constants.AGENT_NET_PATH, Constants.MY_ADDR)+"</h1><hr>\n";
		return ret;
	}
	
	public String getHTMLFooter(){
		String ret = 	"<hr><a href='home'>" + Constants.APP_NAME + "</a> - " + Constants.APP_VERSION +
						"</body></html>\n";
		return ret;
	}
	
	public String getFFtable(String a, String b, String c, String d, String w){
		String ret = 	"<table width='"+w+"' border='0' align='right' cellpadding='8' cellspacing='8'>" +
						"<tr valign='top'><td>"+a+"</td><td>"+b+"</td></tr>" +
						"<tr valign='top'><td>"+c+"</td><td>"+d+"</td></tr>" +
						"</table>";
		return ret;
	}
	
}
