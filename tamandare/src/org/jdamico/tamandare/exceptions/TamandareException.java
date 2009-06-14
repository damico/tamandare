package org.jdamico.tamandare.exceptions;


public class TamandareException extends Exception {

	private static final long serialVersionUID = -5433172273983353912L;
	
	private StackTraceElement[] stackTraceElements;
	
	public TamandareException(String message){
		super(message);
	}
	
	public TamandareException(){
		super();
	}
	
	public TamandareException(StackTraceElement[] stackTraceElements) {
		this.stackTraceElements = stackTraceElements;
	}

	public String getStackTraceElements(){
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < stackTraceElements.length; i++){
			sb.append(stackTraceElements[i].getFileName()+"("+stackTraceElements[i].getLineNumber()+")\n");
		}
		return sb.toString();
	}
	

}
