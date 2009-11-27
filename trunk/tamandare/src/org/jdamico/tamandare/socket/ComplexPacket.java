package org.jdamico.tamandare.socket;

import org.jdamico.tamandare.exceptions.TamandareException;
import org.jdamico.tamandare.utils.TamandareHelper;

public class ComplexPacket {
	private String type;
	private String value;
	private String toAddr;
	private String fromAddr;
	

	private String sComplexPacket;
	
	public ComplexPacket(String toAddr, String type, String value, String fromAddr) {
		this.toAddr = toAddr;
		this.fromAddr = fromAddr;
		this.type = type;
		this.value = value;
		this.sComplexPacket = TamandareHelper.getInstance().complexPacket2String(toAddr, type, value, fromAddr);
	}
	
	
	public String getSComplexPacket() {
		return sComplexPacket;
	}




	public void setSComplexPacket(String complexPacket) throws TamandareException {
		ComplexPacket cp = TamandareHelper.getInstance().string2ComplexPacket(complexPacket);
		this.toAddr = cp.getToAddr();
		this.fromAddr = cp.getFromAddr();
		this.type = cp.getType();
		this.value = cp.getValue();
		this.sComplexPacket = complexPacket;
	}




	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
		
	public String getToAddr() {
		return toAddr;
	}


	public void setToAddr(String toAddr) {
		this.toAddr = toAddr;
	}


	public String getFromAddr() {
		return fromAddr;
	}


	public void setFromAddr(String fromAddr) {
		this.fromAddr = fromAddr;
	}
}
