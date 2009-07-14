package org.jdamico.tamandare.tests;

import java.util.Date;

import org.jdamico.tamandare.components.ConverterFactory;
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
			xml = ConverterFactory.getConverter(Constants.LINK, link).exec();
			ret = transactionManager.saveDoc(xml);
			System.err.println(xml);
		} catch (TamandareException e) {

			try {
				xml = ConverterFactory.getConverter(Constants.ERROR, new ErrorObject(1, e.getStackTraceElements())).exec();
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		assertTrue(ret);
		System.out.println(xml);
	}

}
