package org.jdamico.tamandare.dataobjects;

import java.util.Date;
import java.util.StringTokenizer;

import org.jdamico.tamandare.utils.Constants;

public class LinkObject implements TamandareXMLObject {

	private TamandareHeader header =  new TamandareHeader();
	private TamandareBody body = new TamandareBody();
	
	public LinkObject(String url, String tags, Date date, TamandareReturn tReturn) {
		header.setDate(date);
		header.setType(Constants.LINK);
		header.setMessageReturn(tReturn);
		setHeader(header);
		
		StringTokenizer st = new StringTokenizer(tags, " ");
		String[] tagArray = new String[st.countTokens()]; 
		int count = 0;
		while (st.hasMoreElements()) {
			tagArray[count] = st.nextToken();
			count++;
		}
		body.setTags(tagArray);
		body.setUrl(url);
		setBody(body);
	}


	public TamandareBody getBody() {
		return body;
	}

	public TamandareHeader getHeader() {
		return header;
	}

	public void setBody(TamandareBody body) {
		this.body = body;
	}

	public void setHeader(TamandareHeader header) {
		this.header = header;
	}

}
