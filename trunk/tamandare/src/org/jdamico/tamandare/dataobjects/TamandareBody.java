package org.jdamico.tamandare.dataobjects;

public class TamandareBody implements Content {

	private String url;
	
	private String signature;
	
	private String entity;
	
	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	private String entityType;
	
	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	private String[] tagArray;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setTags(String[] tagArray) {
		this.tagArray = tagArray;
	}

	public String[] getTags() {
		return tagArray;
	}
	
}
