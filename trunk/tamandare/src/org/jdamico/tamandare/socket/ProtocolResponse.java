package org.jdamico.tamandare.socket;

import org.jdamico.tamandare.utils.TamandareHelper;

public class ProtocolResponse {
private String outputMessage;
private ComplexPacket complexPacket;
	
	public ProtocolResponse(String outputMessage, int state) {
		this.outputMessage = outputMessage;
		this.state = state;
	}
	
	
	
	public ProtocolResponse(ComplexPacket complexPacket, int state) {
		this.outputMessage = TamandareHelper.getInstance().complexPacket2String(complexPacket.getAddr(), complexPacket.getType(), complexPacket.getValue());
		this.state = state;
	}



	public ComplexPacket getComplexPacket() {
		return complexPacket;
	}



	public void setComplexPacket(ComplexPacket complexPacket) {
		this.complexPacket = complexPacket;
	}



	public String getOutputMessage() {
		return outputMessage;
	}
	public void setOutputMessage(String outputMessage) {
		this.outputMessage = outputMessage;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	private int state;
}
