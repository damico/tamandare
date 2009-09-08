package org.jdamico.tamandare.components;

import java.util.Date;
import java.util.Map;
import java.util.StringTokenizer;

import org.jdamico.tamandare.dataobjects.Combo;
import org.jdamico.tamandare.dataobjects.EntityObject;
import org.jdamico.tamandare.dataobjects.TamandareReturn;
import org.jdamico.tamandare.dataobjects.TamandareXMLObject;
import org.jdamico.tamandare.exceptions.TamandareException;
import org.jdamico.tamandare.transactions.TransactionManager;
import org.jdamico.tamandare.utils.Constants;

public class EntityManager extends TamandareObjectManager {
	private static EntityManager INSTANCE = null;
	public static EntityManager getInstance(){
		if(INSTANCE == null) INSTANCE = new EntityManager();
		return INSTANCE;
	}
	
	public Combo storeEntity(String type, String signature) throws TamandareException{
		Date date = new Date();
		Combo combo = new Combo();
		
		String[] signaturePair = EntityManager.getInstance().parseSignature(signature);
		
		if(signaturePair[1] == null || signaturePair[0] == null) throw new TamandareException("Invalid signature");
		
		TamandareXMLObject entityObj = new EntityObject(type, signaturePair[1], signaturePair[0], date, new TamandareReturn(0, Constants.ENTITY_FINE_ADDED));
		String xml = null;
		TransactionManager transactionManager = new TransactionManager();
		try {
		
			xml = Converter2XMLFactory.getConverter(Constants.ENTITY, entityObj).exec();
			
			if(!transactionManager.isEntitystored(entityObj.getBody().getSignature())){
				transactionManager.saveDoc(xml, null, null);
				combo.setXmlObj(entityObj);
				combo.setXml(xml);
			}else{
				/* builds an error xml with an inner exception */
				combo = setErrorXML(new TamandareException(Constants.ENTITY_ALREADY_ADDED), combo);
			}
			
		} catch (TamandareException e) {

			combo = setErrorXML(e, combo);
		}
		
		System.err.println(combo.getXml());
		
		return combo;
	}

	private String[] parseSignature(String signature) {
		String[] ret = new String[2];
		StringTokenizer st = new StringTokenizer(signature, Constants.SIGNATURE_DELIMITER);
		ret[0] = st.nextToken();
		ret[1] = st.nextToken();
		return ret;
	}

	public Map<Integer, String> getEntities() {
		TransactionManager transactionManager = new TransactionManager();
		return transactionManager.getDocsByType(Constants.ENTITY, "//tamandare/body/signature/content");
	}

	public Map<Integer, String> getEntityNames() {
		TransactionManager transactionManager = new TransactionManager();
		return transactionManager.getDocsByType(Constants.ENTITY, "//tamandare/body/signature/@entity");
	}
}
