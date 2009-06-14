package org.jdamico.tamandare.dataobjects;

public interface TamandareXMLObject {
	public TamandareHeader getHeader();
	public TamandareBody getBody();
	public void setHeader(TamandareHeader header);
	public void setBody(TamandareBody body);
}
