package org.jdamico.tamandare.components;

import java.util.Date;

import org.jdamico.tamandare.dataobjects.Combo;
import org.jdamico.tamandare.dataobjects.ErrorObject;
import org.jdamico.tamandare.dataobjects.SysMessageObject;
import org.jdamico.tamandare.dataobjects.TamandareReturn;
import org.jdamico.tamandare.dataobjects.TamandareXMLObject;
import org.jdamico.tamandare.exceptions.TamandareException;
import org.jdamico.tamandare.transactions.TransactionManager;
import org.jdamico.tamandare.utils.Constants;

public class TamandareObjectManager {

	public TamandareObjectManager() {
		super();
	}

	public Combo setErrorXML(TamandareException e, Combo combo) {
		String xml = null;
		
		TamandareXMLObject errObj = new ErrorObject(1, e.getMessage());
		combo.setXmlObj(errObj);
		try {
			xml = Converter2XMLFactory.getConverter(Constants.ERROR, errObj).exec();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		combo.setXml(xml);
		return combo;
	}

	protected Combo setSuccessXML(String message) {
		
		
		TamandareReturn tReturn = new TamandareReturn(0, message);
		TamandareXMLObject tObj = new SysMessageObject(new Date(), tReturn);
		String xml = null;
	
		Combo combo = new Combo();
	
	
		combo.setXmlObj(tObj);
		try {
			xml = Converter2XMLFactory.getConverter(Constants.SYSMSG, tObj).exec();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		combo.setXml(xml);
		return combo;
	}

	public Combo delete(int docid) {
		TransactionManager transactionManager = new TransactionManager();
		transactionManager.delete(docid);
		Combo combo = setSuccessXML("Document deleted.");
		return combo;
	}

}