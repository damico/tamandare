package org.jdamico.tamandare.dataobjects;

public class TamandareReturn {
	private String returnMsg;
	private String returnCode;
	
	public TamandareReturn(int i, String message) {
		setReturnCode(String.valueOf(i));
		setReturnMsg(message);
	}
	public String getReturnMsg() {
		return returnMsg;
	}
	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}
	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
}
