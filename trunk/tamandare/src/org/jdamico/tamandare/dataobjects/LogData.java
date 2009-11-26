package org.jdamico.tamandare.dataobjects;

public class LogData {
	private String logName;
	private String logPrefix;
	private long logSize;
	private int logLines;
	
	public int getLogLines() {
		return logLines;
	}
	public void setLogLines(int logLines) {
		this.logLines = logLines;
	}
	public String getLogName() {
		return logName;
	}
	public void setLogName(String logName) {
		this.logName = logName;
	}
	public String getLogPrefix() {
		return logPrefix;
	}
	public void setLogPrefix(String logPrefix) {
		this.logPrefix = logPrefix;
	}
	public long getLogSize() {
		return logSize;
	}
	public void setLogSize(long logSize) {
		this.logSize = logSize;
	}
}

