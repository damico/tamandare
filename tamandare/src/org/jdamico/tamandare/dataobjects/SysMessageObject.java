package org.jdamico.tamandare.dataobjects;

import java.util.Date;

import org.jdamico.tamandare.utils.Constants;

public class SysMessageObject implements TamandareXMLObject {
	
	private TamandareHeader header =  new TamandareHeader();
	private TamandareBody body = new TamandareBody();
	
	public SysMessageObject(Date date, TamandareReturn tReturn){
		header.setDate(date);
		header.setType(Constants.SYSMSG);
		header.setMessageReturn(tReturn);
		setHeader(header);
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
