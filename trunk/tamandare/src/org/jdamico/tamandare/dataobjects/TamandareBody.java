package org.jdamico.tamandare.dataobjects;

public class TamandareBody implements Content {

	private String url;
	
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
