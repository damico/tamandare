package org.jdamico.tamandare.tests;

import junit.framework.TestCase;

public class StoreUrlTest extends TestCase {
	public void testStoreUtlTest(){
		String url;
		String tags;
		Date date;
		LinkObject link = new LinkObject(url, tags, date);
		TransactionManager transactionManager = new TransactionManager();
		assertTrue(transactionManager.save(link));
	}
}
