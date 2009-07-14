package org.jdamico.tamandare.components;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

import org.jdamico.tamandare.dataobjects.Combo;
import org.jdamico.tamandare.dataobjects.ErrorObject;
import org.jdamico.tamandare.dataobjects.LinkObject;
import org.jdamico.tamandare.dataobjects.TamandareReturn;
import org.jdamico.tamandare.dataobjects.TamandareXMLObject;
import org.jdamico.tamandare.exceptions.TamandareException;
import org.jdamico.tamandare.transactions.TransactionManager;
import org.jdamico.tamandare.utils.Constants;

public class URLManager {
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
		
		TamandareXMLObject link = new LinkObject(url, tags, date, new TamandareReturn(0, Constants.FINE_ADDED));
		String xml = null;
		TransactionManager transactionManager = new TransactionManager();
		try {
			xml = ConverterFactory.getConverter(Constants.LINK, link).exec();
			transactionManager.saveDoc(xml);
			combo.setXmlObj(link);
		} catch (TamandareException e) {

			TamandareXMLObject errObj = new ErrorObject(1, e.getMessage() + " | " + e.getStackTraceElements());
			combo.setXmlObj(errObj);
			try {
				xml = ConverterFactory.getConverter(Constants.ERROR, errObj).exec();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
		
		combo.setXml(xml);
		return combo;
	}
	
	public void search(String key){
		TransactionManager transactionManager = new TransactionManager();
		transactionManager.search(key);
	}
	
}
