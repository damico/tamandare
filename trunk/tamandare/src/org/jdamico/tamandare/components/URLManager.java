package org.jdamico.tamandare.components;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.jdamico.tamandare.dataobjects.Combo;
import org.jdamico.tamandare.dataobjects.LinkObject;
import org.jdamico.tamandare.dataobjects.TamandareReturn;
import org.jdamico.tamandare.dataobjects.TamandareXMLObject;
import org.jdamico.tamandare.exceptions.TamandareException;
import org.jdamico.tamandare.threads.ThreadRunnableManager;
import org.jdamico.tamandare.transactions.TransactionManager;
import org.jdamico.tamandare.utils.Constants;
import org.jdamico.tamandare.utils.HashManager;
import org.jdamico.tamandare.utils.TamandareHelper;

public class URLManager extends TamandareObjectManager {
	private static URLManager INSTANCE = null;
	public static URLManager getInstance(){
		if(INSTANCE == null) INSTANCE = new URLManager();
		return INSTANCE;
	}
	
	public Combo storeURL(String url, String tags) throws TamandareException{
		
		LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "storeURL(String url, String tags)");
		
		Date date = new Date();
		Combo combo = new Combo();
		
		try {
			url = URLEncoder.encode(url, "UTF-8");
		} catch (UnsupportedEncodingException e2) {
			throw new TamandareException("Error encoding url.");
		}
		
		HashManager hm = new HashManager();
		String urlHash = hm.getHash(url);
		String tagsHash = hm.getHash(tags.trim());

		
		TamandareXMLObject link = new LinkObject(url, tags, date, new TamandareReturn(0, Constants.URL_FINE_ADDED));
		String xml = null;
		TransactionManager transactionManager = new TransactionManager();
		try {
		
			xml = Converter2XMLFactory.getConverter(Constants.LINK, link).exec();
			
			if(!transactionManager.isURLstored(link.getBody())){
				transactionManager.saveDoc(xml, urlHash, tagsHash);
				combo.setXmlObj(link);
				combo.setXml(xml);
			}else{
				/* builds an error xml with an inner exception */
				combo = setErrorXML(new TamandareException(Constants.URL_ALREADY_ADDED), combo);
			}
			
		} catch (TamandareException e) {

			combo = setErrorXML(e, combo);
		}
		
		LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "combo.getXml() "+combo.getXml());
		
		return combo;
	}
	
	public Map<Integer, String> search(String key){
		
		LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "search(String key)");
		
		TransactionManager transactionManager = new TransactionManager();
		return transactionManager.search(key);
	}

	public ArrayList<String> getTags() {
		
		LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "getTags()");
		
		TransactionManager transactionManager = new TransactionManager();
		return transactionManager.getTags();
	}
	
	public ArrayList<String> getTagsById(int id) {
		
		LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "getTagsById(int id)");
		
		TransactionManager transactionManager = new TransactionManager();
		return transactionManager.getTagsById(id);
	}
	
	public Combo getDocById(int id) throws TamandareException {
		
		LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "getDocById(int id)");
		
		TransactionManager transactionManager = new TransactionManager();
		String[] ret = transactionManager.getDocById(id);
		TamandareXMLObject tObj = Converter2ObjFactory.getConverter(Constants.LINK, ret[2]).exec();
		Combo combo = new Combo(ret[2], tObj, ret[0], ret[1], id);
		return combo;
	}
	
	public boolean isURLstored(String url){
		
		LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "isURLstored(String url)");
		
		TransactionManager transactionManager = new TransactionManager();
		return transactionManager.isURLstored(url);
	}

	public Combo updateURL(String sourceTagsHash, String tags, String stringDocId, String xml) {
		
		LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "updateURL(String sourceTagsHash, String tags, String stringDocId, String xml)");
		
		Combo combo = null;
		boolean upd =  false;
		
		HashManager hm = new HashManager();
		tags = tags.replaceAll("\n", " ");
		String newTagsHash = hm.getHash(tags.trim());
		
		int docId = Integer.parseInt(stringDocId);
		
		TamandareXMLObject tObj = null;
		try {
			tObj = Converter2ObjFactory.getConverter(Constants.LINK, xml).exec();
		} catch (TamandareException e1) {
			e1.printStackTrace();
		}
		
		tObj.getBody().setTags(TamandareHelper.getInstance().tags2Array(tags));
		
		try {
			xml = Converter2XMLFactory.getConverter(Constants.LINK, tObj).exec();
		} catch (TamandareException e1) {
			e1.printStackTrace();
		}
		
		LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "xml: "+xml);
		
		if(sourceTagsHash.equals(newTagsHash)){
			combo = setSuccessXML("Nothing to update!");	
		}else{
			TransactionManager transactionManager = new TransactionManager();
			try {
				upd = transactionManager.update(docId, xml);
				
				combo = setSuccessXML("Document updated!");
				
			} catch (TamandareException e) {
				combo = setErrorXML(e, combo);
				e.printStackTrace();
			}
		}

		return combo;
	}

	public ArrayList<String> getTagsByIntersection(String[] tagsArray) {
		ArrayList<String> myTags = getTags();
		ArrayList<String> intersectionTags =  new ArrayList<String>();
		myTags = TamandareHelper.getInstance().stripTags(myTags);
			for(int i=0; i<tagsArray.length; i++){
				if(myTags.contains(tagsArray[i])){
					intersectionTags.add(tagsArray[i]);
					LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "Common Tags: "+tagsArray[i]);
				}
			}
	    
		return intersectionTags;
	}

	public void sendUrlsByTagsIntersection(String remoteXmlTags, String remotePeer) {
		TamandareXMLObject tObj = null;
		TransactionManager tm = new TransactionManager();
		try {
			tObj = Converter2ObjFactory.getConverter(Constants.LINK, remoteXmlTags).exec();
		} catch (TamandareException e) {
			e.printStackTrace();
		}
		Map<String, String> docsMap = null;
		ArrayList<String> intersectionTagsArray = getTagsByIntersection(tObj.getBody().getTags());
		for(int i=0; i<intersectionTagsArray.size(); i++){
			docsMap = tm.getDocsByTag(intersectionTagsArray.get(i));
		}
		if(docsMap!=null) ThreadRunnableManager.getInstance().sendMyDocsThread(remotePeer, docsMap);
		ThreadRunnableManager.getInstance().sendIntersectionTagsThread(remotePeer, intersectionTagsArray);
		
		
	}

	public void saveDocByXml(String doc) {

		
			LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "saveDocByXml(String doc)");
			Combo combo = new Combo();
			
			TransactionManager transactionManager = new TransactionManager();
			try {
				TamandareXMLObject tObj = Converter2ObjFactory.getConverter(Constants.LINK, doc).exec();
				
				HashManager hm = new HashManager();
				String urlHash = hm.getHash(tObj.getBody().getUrl().trim());
				String tags = TamandareHelper.getInstance().tagsArray2String(tObj.getBody().getTags(), false);
				String tagsHash = hm.getHash(tags.trim());
				if(!transactionManager.isURLstored(urlHash)){
					transactionManager.saveDoc(doc, urlHash, tagsHash);
					combo.setXmlObj(tObj);
					combo.setXml(doc);
				}else{
					/* builds an error xml with an inner exception */
					combo = setErrorXML(new TamandareException(Constants.URL_ALREADY_ADDED), combo);
				}
				
			} catch (TamandareException e) {

				combo = setErrorXML(e, combo);
			}
			
			LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "combo.getXml() "+combo.getXml());

			
			
			
		
		
						
	}

	public void postDocByTag(String backTag, String remotePeer) {
		TransactionManager tm = new TransactionManager();
		Map<String, String> backDocsMap = tm.getDocsByTag(backTag);
		
		
		ThreadRunnableManager.getInstance().sendMyDocsThread(remotePeer, backDocsMap);
		
		
		
	}

	
}
