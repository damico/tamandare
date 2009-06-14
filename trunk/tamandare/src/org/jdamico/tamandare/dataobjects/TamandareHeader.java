package org.jdamico.tamandare.dataobjects;

import java.util.Date;

public class TamandareHeader {
	
	private TamandareReturn messageReturn;
	private int type;
	private Date date;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public TamandareReturn getMessageReturn() {
		return messageReturn;
	}
	public void setMessageReturn(TamandareReturn messageReturn) {
		this.messageReturn = messageReturn;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
}
