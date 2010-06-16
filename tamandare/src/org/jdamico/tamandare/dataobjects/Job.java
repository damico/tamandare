package org.jdamico.tamandare.dataobjects;

import java.util.Date;

public class Job {
	
	private int id = -1;
	private String thread;
	private int interval = -1;
	private Date timestamp = null;
	
	
	
	public Job(int id, String thread, int interval, Date timestamp) {
		super();
		this.id = id;
		this.thread = thread;
		this.interval = interval;
		this.timestamp = timestamp;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getThread() {
		return thread;
	}
	public void setThread(String thread) {
		this.thread = thread;
	}
	public int getInterval() {
		return interval;
	}
	public void setInterval(int interval) {
		this.interval = interval;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	

}
