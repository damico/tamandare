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
		
		System.err.println(combo.getXml());
		
		return combo;
	}
	
	public Map<Integer, String> search(String key){
		TransactionManager transactionManager = new TransactionManager();
		return transactionManager.search(key);
	}

	public ArrayList<String> getTags() {
		TransactionManager transactionManager = new TransactionManager();
		return transactionManager.getTags();
	}
	
	public ArrayList<String> getTagsById(int id) {
		TransactionManager transactionManager = new TransactionManager();
		return transactionManager.getTagsById(id);
	}
	
	public Combo getDocById(int id) throws TamandareException {
		TransactionManager transactionManager = new TransactionManager();
		String[] ret = transactionManager.getDocById(id);
		TamandareXMLObject tObj = Converter2ObjFactory.getConverter(Constants.LINK, ret[2]).exec();
		Combo combo = new Combo(ret[2], tObj, ret[0], ret[1], id);
		return combo;
	}
	
	public boolean isURLstored(String url){
		TransactionManager transactionManager = new TransactionManager();
		return transactionManager.isURLstored(url);
	}

	public Combo updateURL(String sourceTagsHash, String tags, String stringDocId, String xml) {
		
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
		
		System.err.println(xml);
		
		if(sourceTagsHash.equals(newTagsHash)){
			combo = setSuccessXML("Nothing to update!");	
		}else{
			TransactionManager transactionManager = new TransactionManager();
			try {
				upd = transactionManager.update(docId, xml);
				
				System.err.println("+++++++++++++++++++++++++++++   "+upd);
						
				combo = setSuccessXML("Document updated!");
				
			} catch (TamandareException e) {
				combo = setErrorXML(e, combo);
				e.printStackTrace();
			}
		}

		return combo;
	}

	
}
