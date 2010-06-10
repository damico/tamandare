package org.jdamico.tamandare.transactions;

import java.util.ArrayList;
import java.util.Map;

import org.jdamico.tamandare.components.LoggerManager;
import org.jdamico.tamandare.dataobjects.TamandareBody;
import org.jdamico.tamandare.exceptions.TamandareException;
import org.jdamico.tamandare.utils.Constants;

public class TransactionManager {

	public boolean saveDoc(String xml, String urlHash, String tagsHash) throws TamandareException {
		return Derbymanager.getInstance().saveDocument(xml, urlHash, tagsHash);
	}

	public Map<Integer, String> search(String key) {
		return Derbymanager.getInstance().search(key);
	}

	public ArrayList<String> getTags() {
		return Derbymanager.getInstance().getTags();
	}

	public boolean isURLstored(String urlHash) {
		return Derbymanager.getInstance().isURLstored(urlHash);
	}
	
	public boolean isURLstored(TamandareBody body) throws TamandareException {
		Derbymanager dm = new Derbymanager();
		return dm.isURLstored(body);
	}

	public void delete(int docid) {
		Derbymanager.getInstance().delete(docid);
		
	}

	public ArrayList<String> getTagsById(int id) {
		return Derbymanager.getInstance().getTagsById(id);
	}

	public String[] getDocById(int id) {
		return Derbymanager.getInstance().getDocById(id);
	}

	public boolean update(int docId, String xml) throws TamandareException {
		return Derbymanager.getInstance().update(docId, xml);
	}

	public boolean isEntitySignatureStored(String signature) {
		return Derbymanager.getInstance().isEntitySignatureStored(signature);
	}

	public Map<Integer, String> getDocsByType(int docType, String docPath) {
		return Derbymanager.getInstance().getDocsByType(docType, docPath);
	}

	public boolean isEntityNameStored(String value) {
		return Derbymanager.getInstance().isEntityNameStored(value);
	}

	public Map<String, String> getDocsByTag(String tag) {
		return Derbymanager.getInstance().getDocsByTag(tag);
	}
	
	public void checkDB() throws TamandareException{
		ArrayList<String> schemas = Derbymanager.getInstance().getSchemas();
		if(!schemas.contains(Constants.APP_SCHEMA)){
			Derbymanager.getInstance().prepareDB();
			LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "DB not ready, creating basic table.");
		}else LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "DB ready!");

	}
	
}
