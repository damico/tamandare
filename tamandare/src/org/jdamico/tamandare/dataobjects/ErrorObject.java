package org.jdamico.tamandare.dataobjects;

import java.util.Date;

import org.jdamico.tamandare.utils.Constants;

public class ErrorObject implements TamandareXMLObject {
	
	private TamandareHeader header =  new TamandareHeader();
	private TamandareBody body = new TamandareBody();

	public ErrorObject(int i, String message) {
		header.setDate(new Date());
		header.setType(Constants.ERROR);
		header.setMessageReturn(new TamandareReturn(i, message));
		setHeader(header);
		setBody(null);
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
