package org.jdamico.tamandare.dataobjects;

public class HistoryConnection {
	private String entityName;
	private String host;
	
	public HistoryConnection(String host, String entityName) {
		super();
		this.entityName = entityName;
		this.host = host;
	}
	
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	
}
