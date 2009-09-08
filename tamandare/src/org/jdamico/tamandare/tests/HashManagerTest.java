package org.jdamico.tamandare.tests;

import org.jdamico.tamandare.utils.HashManager;

import junit.framework.TestCase;

public class HashManagerTest extends TestCase {
	public void testHash(){
		HashManager hm = new HashManager();
		System.out.println(hm.getHash("damico"));
	}
}
