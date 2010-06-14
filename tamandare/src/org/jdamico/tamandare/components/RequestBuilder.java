package org.jdamico.tamandare.components;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.jdamico.tamandare.exceptions.TamandareException;


public class RequestBuilder {
	private static RequestBuilder INSTANCE = null;
	public static RequestBuilder getInstance(){
		if(INSTANCE == null) INSTANCE = new RequestBuilder();
		return INSTANCE;
	}
	public void sendPost(String param, String xml, String host) throws TamandareException{
		
		
		
		LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "sendPost param: "+param);
		LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "sendPost xml: "+xml);
		LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "sendPost host: "+host);
		
		try {
	        // Construct data
	        String data = URLEncoder.encode(param, "UTF-8") + "=" + URLEncoder.encode(xml, "UTF-8");
	        // Send data
	        URL url = new URL("http://"+host+":8989/RemoteRequest?");
	        URLConnection conn = url.openConnection();
	        conn.setDoOutput(true);
	        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
	        wr.write(data);
	        wr.flush();
	    
	        // Get the response
	        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        String line;
	        while ((line = rd.readLine()) != null) {
	            System.err.println("===========> "+line);
	        }
	        wr.close();
	        rd.close();
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	throw new TamandareException(e.getStackTrace());
	    	
	    }

	}

}
