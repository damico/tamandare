package org.jdamico.tamandare.dataobjects;

import java.util.Date;

import org.jdamico.tamandare.utils.Constants;
import org.jdamico.tamandare.utils.TamandareHelper;

public class LinkObject implements TamandareXMLObject {

	private TamandareHeader header =  new TamandareHeader();
	private TamandareBody body = new TamandareBody();
	
	public LinkObject(String url, String tags, Date date, TamandareReturn tReturn) {
		header.setDate(date);
		header.setType(Constants.LINK);
		header.setMessageReturn(tReturn);
		setHeader(header);
		body.setTags(TamandareHelper.getInstance().tags2Array(tags));
		body.setUrl(url);
		setBody(body);
	}


	public LinkObject() {
		super();
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
