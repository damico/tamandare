package org.jdamico.tamandare.transactions;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Map;

import org.jdamico.tamandare.components.Converter2ObjFactory;
import org.jdamico.tamandare.components.LoggerManager;
import org.jdamico.tamandare.dataobjects.HistoryConnection;
import org.jdamico.tamandare.dataobjects.TamandareBody;
import org.jdamico.tamandare.dataobjects.TamandareXMLObject;
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
		ArrayList<String> ret = null;
		Derbymanager dm =  new Derbymanager();
		try {
			ret = dm.getTags();
		} catch (TamandareException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
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
		Map<String, String> ret = null;
		if(tag!=null && !tag.equals("")){
			try {
				Derbymanager dm =  new Derbymanager();
				ret = dm.getDocsByTag(tag);
			} catch (TamandareException e) {
				e.printStackTrace();
			}
		}
		return ret;
	}

	public void checkDB() throws TamandareException{
		int check = 0;
		ArrayList<String> schemas = Derbymanager.getInstance().getSchemas();

		if(!schemas.contains(Constants.APP_SCHEMA)){
			for(int i=0; i<Constants.DEFAULT_TABLES.length; i++){
				Derbymanager.getInstance().prepareDB(Constants.DEFAULT_TABLES[i]);
				LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "DB not ready, creating default tables. ["+Constants.DEFAULT_TABLES[i]+"]");
			}
			check++;
		}else{
			ArrayList<String> existentTables = Derbymanager.getInstance().getExistentTables();
			for(int i=0; i<Constants.DEFAULT_TABLES.length; i++){

				if(!existentTables.contains(Constants.DEFAULT_TABLES_NAMES[i])){
					Derbymanager.getInstance().prepareDB(Constants.DEFAULT_TABLES[i]); 
					LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "DB not ready, creating default tables. ["+Constants.DEFAULT_TABLES[i]+"]");
				}
				
			}
			check++;
		}
		if(check == 0) LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "DB ready!");
	}

	public TamandareXMLObject getDocByEntityName(String entityName) {
		TamandareXMLObject tObj = null;
		try {
			String xml = Derbymanager.getInstance().getXMLbyEntityName(entityName);

			LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), xml);

			tObj = Converter2ObjFactory.getConverter(Constants.LINK, xml).exec();

			if(tObj.getBody()==null) throw new TamandareException("TamandareBody is null", this.getClass().getName());

		} catch (TamandareException e) {
			LoggerManager.getInstance().logAtExceptionTime(this.getClass().getName(), e.getMessage());
			e.printStackTrace();
		}
		return tObj;
	}

	public boolean isMachine(TamandareXMLObject tObj){
		boolean ret = false;
		if(tObj.getBody().getEntityType()!=null && tObj.getBody().getEntityType().equalsIgnoreCase("machine")) ret = true;
		return ret;
	}

	public boolean isMachine(String entityName){
		boolean ret = false;
		ret= isMachine(getDocByEntityName(entityName));
		return ret;
	}

	public void saveInHistoryConn(String host, String entityName) {
		try {
			Derbymanager.getInstance().saveInHistoryConn(host, entityName);
			LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "saveInHistoryConn("+host+","+entityName+")");
		} catch (TamandareException e) {
			LoggerManager.getInstance().logAtExceptionTime(this.getClass().getName(), e.getMessage());
		} catch (SQLIntegrityConstraintViolationException e) {
			LoggerManager.getInstance().logAtExceptionTime(this.getClass().getName(), e.getMessage());
		}
	}

	public ArrayList<HistoryConnection> getHistoryConn() {
		ArrayList<HistoryConnection> hConnections = null;
		try {
			hConnections = Derbymanager.getInstance().getHistoryConn();
		} catch (TamandareException e) {
			LoggerManager.getInstance().logAtExceptionTime(this.getClass().getName(), e.getMessage());
			e.printStackTrace();
		}
		return hConnections;
	}

}
