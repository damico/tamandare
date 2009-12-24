package org.jdamico.tamandare.dataobjects;

import java.util.Date;

import org.jdamico.tamandare.utils.Constants;

public class TagsObject implements TamandareXMLObject {
	
	private TamandareHeader header =  new TamandareHeader();
	private TamandareBody body = new TamandareBody();
	
	public TagsObject(String tags[], Date date, TamandareReturn tReturn) {
		header.setDate(date);
		header.setType(Constants.LINK);
		header.setMessageReturn(tReturn);
		setHeader(header);
		body.setTags(tags);
		setBody(body);
	}
	
	public TagsObject(){
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
