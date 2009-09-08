package org.jdamico.tamandare.tests;

import java.util.Date;

import org.jdamico.tamandare.components.Converter2XMLFactory;
import org.jdamico.tamandare.dataobjects.ErrorObject;
import org.jdamico.tamandare.dataobjects.LinkObject;
import org.jdamico.tamandare.dataobjects.TamandareReturn;
import org.jdamico.tamandare.exceptions.TamandareException;
import org.jdamico.tamandare.transactions.TransactionManager;
import org.jdamico.tamandare.utils.Constants;

import junit.framework.TestCase;

public class StoreUrlTest extends TestCase {
	public void testStoreUtlTest(){
		String url = "http://dcon.com.br";
		String tags = "blog damico";
		Date date = new Date();
		
	
		
		LinkObject link = new LinkObject(url, tags, date, new TamandareReturn(0,"success"));
		String xml = null;
		TransactionManager transactionManager = new TransactionManager();
		boolean ret = false;
		try {
			xml = Converter2XMLFactory.getConverter(Constants.LINK, link).exec();
			
			if(!transactionManager.isURLstored(link.getBody().getUrl())){
				ret = transactionManager.saveDoc(xml);
			}else{
				System.out.println("URL already stored!");
			}
			
			
		} catch (TamandareException e) {

			try {
				xml = Converter2XMLFactory.getConverter(Constants.ERROR, new ErrorObject(1, e.getStackTraceElements())).exec();
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
		System.out.println(xml);
		
		assertTrue(ret);
		
	}

}
