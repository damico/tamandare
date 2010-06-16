package org.jdamico.tamandare.utils;

import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class KillAgent {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
	        // Construct data
	        String data = URLEncoder.encode("tip", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8");
	        // Send data
	        URL url = new URL("http://127.0.0.1:8989/killMe?");
	        URLConnection conn = url.openConnection();
	        conn.setDoOutput(true);
	        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
	        wr.write(data);
	        wr.flush();
	        conn.getInputStream();
	        wr.close();
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}

}
