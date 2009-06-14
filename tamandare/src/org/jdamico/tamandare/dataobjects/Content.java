package org.jdamico.tamandare.dataobjects;

public interface Content {
	public void setUrl(String url);
	public String getUrl();
	
	public void setTags(String[] tagArray);
	public String[] getTags();
}
