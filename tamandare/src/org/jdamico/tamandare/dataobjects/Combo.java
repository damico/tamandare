package org.jdamico.tamandare.dataobjects;

public class Combo {
	private String xml;
	private TamandareXMLObject xmlObj;
	private String urlHash;
	private String tagsHash;
	private int docId;
	
	public int getDocId() {
		return docId;
	}
	public void setDocId(int docId) {
		this.docId = docId;
	}
	public String getUrlHash() {
		return urlHash;
	}
	public void setUrlHash(String urlHash) {
		this.urlHash = urlHash;
	}
	public String getTagsHash() {
		return tagsHash;
	}
	public void setTagsHash(String tagsHash) {
		this.tagsHash = tagsHash;
	}
	public Combo(String extXml, TamandareXMLObject obj, String urlHash, String urlTags, int docId) {
		setXml(extXml);
		setXmlObj(obj);
		setUrlHash(urlHash);
		setTagsHash(urlTags);
		setDocId(docId);
	}
	
	public Combo() {
		// TODO Auto-generated constructor stub
	}
	public String getXml() {
		return xml;
	}
	public void setXml(String xml) {
		this.xml = xml;
	}
	public TamandareXMLObject getXmlObj() {
		return xmlObj;
	}
	public void setXmlObj(TamandareXMLObject xmlObj) {
		this.xmlObj = xmlObj;
	}
	
	
	
}
