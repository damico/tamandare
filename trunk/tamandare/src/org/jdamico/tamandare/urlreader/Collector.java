package org.jdamico.tamandare.urlreader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jdamico.tamandare.exceptions.TamandareException;
import org.jdamico.tamandare.transactions.PostgreSQLmanager;
import org.jdamico.tamandare.utils.Constants;

public class Collector {

	public String getUrlText(String urlStr) throws IOException, TamandareException{

		
		
		System.out.println("Entering getUrlText: "+urlStr);
		
		StringBuffer sb = new StringBuffer();

		// Create a URL for the desired page
		URL url = new URL(urlStr);
		
		// Read all the text returned by the server
		
		InputStream urlStream = url.openStream();
		

		BufferedReader in = new BufferedReader(new InputStreamReader(urlStream));
		String str;
		while ((str = in.readLine()) != null) {
			sb.append(str.replaceAll("'", "\""));
		}
		in.close();
		urlStream.close();

		/* maybe put it in a thread to avoid stop the processes */
		PostgreSQLmanager.getInstance().saveUrl(urlStr, 0);
		
		return sb.toString();
	}

	public void getInnerUrls(String htmlContent, String urlStr) throws TamandareException{
		System.out.println("Getting inner urls...");
		PostgreSQLmanager.getInstance().updateUrl(urlStr, Constants.THREAD_BUSY);
		
		String urlPattern = "href";

		htmlContent = htmlContent.replaceAll("\n", "");

		int init = 0;
		int hPos = 0;
		int doubleQuotePosInit = 0;
		int doubleQuotePosEnd = 0;


		for(int i=init; i < htmlContent.length(); i++){
			if(htmlContent.charAt(i)=='h'){
				hPos = i;
				String possibleStr = htmlContent.substring(hPos, hPos+4);
				if(possibleStr.equals(urlPattern)){

					for(int j = hPos+urlPattern.length()+1; j < hPos+urlPattern.length()+5; j++){
						if(htmlContent.charAt(j)=='\"'){
							doubleQuotePosInit = j;

							for(int k = doubleQuotePosInit+1; k < doubleQuotePosInit+150; k++){
								if(k>=htmlContent.length()) break;
								if(htmlContent.charAt(k)=='\"'){
									doubleQuotePosEnd = k;
									String urlReturn = htmlContent.substring(doubleQuotePosInit+1, doubleQuotePosEnd);
									urlReturn = urlReturn.trim();

									if(!urlReturn.contains(" ") && !urlReturn.contains("javascript")){
										
										
										
										if(!urlReturn.contains("http")){
											
											try{
												urlReturn = cd(urlStr, urlReturn);
											} catch (StringIndexOutOfBoundsException e){
												
											}
											
											

										}
										
										if(parseUrl(urlReturn)){
											PostgreSQLmanager.getInstance().saveUrl(urlReturn, -1);
											System.err.println(PostgreSQLmanager.getInstance().getUrlTotal()+": "+urlReturn);
										}
										
										
										init = doubleQuotePosEnd +1;
									}

								}
							}

						}
					}

				}
			}
		}

		/* TODO: 
		 * 1) lock link that was read
		 * 2) update new unlock links
		 */
		PostgreSQLmanager.getInstance().saveUrl(urlStr, 4);
	}

	private String cd(String base, String change) throws StringIndexOutOfBoundsException {
		/* 
		 * ../
		 * ../../
		 * ../../../
		 * ./
		 */
		
		
		
		String result = null; 
		
		base =  base.trim();
		
		
		Map<Integer, String> baseMap = folders2map(base);
		String proto = getProtocol(base);
		StringBuffer sb = new StringBuffer();
		
		if(base.charAt(base.length()-1)=='/') base = base.substring(0,base.length()-1);
		if(change.charAt(0)=='.' && change.charAt(1)=='/'){
			result = getDomain(base)+"/"+change.substring(2, change.length()-1);
		}else if(change.charAt(0)=='.' && change.charAt(1)=='.'  && change.charAt(2)=='/'){
			StringTokenizer st = new StringTokenizer(change, "/");
			int noOfBackFolders = 0;
			


			while(st.hasMoreElements()){
				String element = (String) st.nextElement();
				if(element.equals("..")) noOfBackFolders++;
			}
			
			
			
			int limit = baseMap.size() - noOfBackFolders;
			
			for(int i=0; i<limit; i++){
				sb.append(baseMap.get(i)+"/");
			}
			
			
			
			result = proto +sb.toString()  + change.replaceAll("\\../", "");
		}else{

			for(int i=0; i<baseMap.size(); i++){
				sb.append(baseMap.get(i)+"/");
			}
			result = proto + sb.toString() +change.replaceAll("\\/", "");
		}
		
		return result;
	}

	private String getProtocol(String urlStr){
		String partial = null;
		for(int i=0; i < urlStr.length(); i++){
			if(urlStr.charAt(i)==':' && urlStr.charAt(i+1)=='/' && urlStr.charAt(i+2)=='/') partial = urlStr.substring(0,i+3);
		}
		return partial;
	}

	private String getAfterLastSlash(String urlStr) {
		int slashPos = 0;
		String prefix = null;
		for(int l = urlStr.length()-1; l >= 0; l--){
			if (urlStr.charAt(l)=='/'){
				slashPos = l;
				prefix = urlStr.substring(slashPos+1, urlStr.length()-1);
				break;
			}
			
		}
		return prefix;
	}

	private String stripProtocolFromUrl(String urlStr){
		String partial = urlStr;
		for(int i=0; i < urlStr.length(); i++){
			if(urlStr.charAt(i)==':' && urlStr.charAt(i+1)=='/' && urlStr.charAt(i+2)=='/') partial = urlStr.substring(i+3,urlStr.length()-1);
		}
		return partial;
	}
	
	private String getDomain(String base) {
		String partial = stripProtocolFromUrl(base);
		for(int j=0; j < partial.length(); j++){
			if(partial.charAt(j)=='/') partial = partial.substring(0,j);
		}
			
		
		return partial;
	}
	
	private Map<Integer, String> folders2map(String urlStr){
		String result = getTillLastSlash(urlStr);
		result = stripProtocolFromUrl(result);
		StringTokenizer st = new StringTokenizer(result, "/");
		Map<Integer, String> foldersMap = new HashMap<Integer, String>();
		int counter = 0;
		while(st.hasMoreElements()){
			String element = (String) st.nextElement();
			foldersMap.put(counter, element);
			counter++;
		}
		return foldersMap;
	}
	
	private String getTillLastSlash(String urlStr){
		int slashPos = 0;
		String prefix = null;
		for(int l = urlStr.length()-1; l >= 0; l--){
			if (urlStr.charAt(l)=='/'){
				slashPos = l;
				prefix = urlStr.substring(0,slashPos+1);
				break;
			}
			
		}
		return prefix;
	}

	private boolean parseUrl(String urlStr){
		String r = "^((http[s]?|ftp):\\/)?\\/?([^:\\/\\s]+)(:([^\\/]*))?((\\/\\w+)*\\/)([\\w\\-\\.]+[^#?\\s]+)(\\?([^#]*))?(#(.*))?$";
		Pattern p = Pattern.compile(r);
		Matcher m = p.matcher(urlStr);
		boolean ret = m.matches();

		if(urlStr.contains("javascript|\"")) ret = false;

		return ret;
			
	}
}
