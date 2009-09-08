package org.jdamico.tamandare.socket;

import org.jdamico.tamandare.exceptions.TamandareException;
import org.jdamico.tamandare.utils.TamandareHelper;

public class ComplexPacket {
	private String type;
	private String value;
	private String addr;
	private String sComplexPacket;
	
	public ComplexPacket(String addr, String type, String value) {
		this.addr = addr;
		this.type = type;
		this.value = value;
		this.sComplexPacket = TamandareHelper.getInstance().complexPacket2String(addr, type, value);
	}
	
	
	public String getSComplexPacket() {
		return sComplexPacket;
	}




	public void setSComplexPacket(String complexPacket) throws TamandareException {
		ComplexPacket cp = TamandareHelper.getInstance().string2ComplexPacket(complexPacket);
		this.addr = cp.getAddr();
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
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	
}
