package org.jdamico.tamandare.dataobjects;

import java.util.Date;

import org.jdamico.tamandare.utils.Constants;

public class EntityObject implements TamandareXMLObject {

	private TamandareHeader header =  new TamandareHeader();
	private TamandareBody body = new TamandareBody();
	
	
	public EntityObject(String type, String signature, String entity, Date date, TamandareReturn tReturn) {
		header.setDate(date);
		header.setType(Constants.ENTITY);
		header.setMessageReturn(tReturn);
		setHeader(header);
		body.setSignature(signature);
		body.setEntity(entity);
		body.setEntityType(type);
		setBody(body);
	}


	public EntityObject() {
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
