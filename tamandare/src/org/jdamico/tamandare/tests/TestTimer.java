package org.jdamico.tamandare.tests;

import org.jdamico.tamandare.urlreader.ThreadRunnableManager;
import org.jdamico.tamandare.urlreader.Timer;

import junit.framework.TestCase;

public class TestTimer extends TestCase {
	public void testOne() throws InterruptedException{
//		ThreadRunnableManager trm = new ThreadRunnableManager();
//		trm.startTimeCounter(32);
		
		Thread t1 = new Thread(new Timer(40));
		t1.start();
		
		t1.sleep(1000);
		
		
	}
}
