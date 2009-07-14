package org.jdamico.tamandare.exceptions;

import java.net.URLEncoder;


public class TamandareException extends Exception {

	private static final long serialVersionUID = -5433172273983353912L;
	
	private StackTraceElement[] stackTraceElements;
	
	private String message;
	
	public TamandareException(String message){
		
		super(message);
		this.message = message;
	}
	
	public TamandareException(){
		super();
	}
	
	public TamandareException(StackTraceElement[] stackTraceElements) {
		this.stackTraceElements = stackTraceElements;
	}
	
	public TamandareException(StackTraceElement[] stackTraceElements, String rootMessage) {
		super(rootMessage);
		this.stackTraceElements = stackTraceElements;
		
	}

	public String getStackTraceElements(){
		StringBuffer sb = new StringBuffer();
		
		if(stackTraceElements == null){
			sb.append(message);
		}else{
			for(int i = 0; i < stackTraceElements.length; i++){
				sb.append(stackTraceElements[i].getFileName()+"("+stackTraceElements[i].getLineNumber()+")\n");
			}
		}
		
		
		return sb.toString();
	}
	

}
