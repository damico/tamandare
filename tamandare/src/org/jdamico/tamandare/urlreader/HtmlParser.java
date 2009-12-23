package org.jdamico.tamandare.urlreader;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.jdamico.tamandare.exceptions.TamandareException;
import org.jdamico.tamandare.transactions.PostgreSQLmanager;
import org.jdamico.tamandare.utils.Constants;

public class HtmlParser {


	public String getTitle(String urlStr, String htmlContent) throws UnsupportedEncodingException{
		
		System.out.println("Getting title...");
		String content = null;

		try {
			PostgreSQLmanager.getInstance().updateUrl(urlStr, Constants.THREAD_BUSY);
		} catch (TamandareException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(int i=0; i<htmlContent.length(); i++){
			/* 
			 * <title>
			 */
			int titleInit = 0;
			int titleEnd = 0;
			if(htmlContent.charAt(i)=='<' && htmlContent.charAt(i+1)=='t' && htmlContent.charAt(i+2)=='i'
				&& htmlContent.charAt(i+3)=='t' && htmlContent.charAt(i+4)=='l' && htmlContent.charAt(i+5)=='e'
					&& htmlContent.charAt(i+6)=='>'){
				titleInit = i + 6 + 1;
				for(int j=titleInit; j<htmlContent.length(); j++){

					if(htmlContent.charAt(j)=='<' && htmlContent.charAt(j+1)=='/' && htmlContent.charAt(j+2)=='t'
						&& htmlContent.charAt(j+3)=='i' && htmlContent.charAt(j+4)=='t' && htmlContent.charAt(j+5)=='l'
							&& htmlContent.charAt(j+6)=='e' && htmlContent.charAt(j+7)=='>'){
						titleEnd = j;
						break;
					}

				}
				content = URLEncoder.encode(htmlContent.substring(titleInit, titleEnd),"UTF-8");
			}
		}

		return content;
	}
	
	public String getMeta(String htmlContent) throws UnsupportedEncodingException{
		System.out.println("Getting meta...");
		String content = null;
		int metaInit = 0;
		int metaEnd = 0;

		
		
		for(int i=0; i<htmlContent.length(); i++){


			String tag = "meta name=\"keywords\" content=\"";
			              

			
			StringBuffer sb = new StringBuffer();
			
			if(htmlContent.charAt(i)=='<'){
				for(int k = 0; k < tag.length(); k++){
					if((i+k+1)>=htmlContent.length()) break;
					if(htmlContent.charAt(i+k+1)==tag.charAt(k)){
						sb.append(tag.charAt(k));
						metaInit = k+i+1;
					}
					
				}
				
				if(sb.toString().equals(tag)){		
					
					for(int j=metaInit; j<htmlContent.length(); j++){
						if(htmlContent.charAt(j)=='>'){
							metaEnd = j-1; break;
						}
					}
					content = htmlContent.substring(metaInit,metaEnd);
					System.err.println("kkkkkkkkkkkkkkk "+content);
					content = URLEncoder.encode(content,"UTF-8");
				}
			}
		}	
		return content;
	}

}
