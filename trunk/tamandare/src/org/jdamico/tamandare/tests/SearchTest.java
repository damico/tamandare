package org.jdamico.tamandare.tests;

import org.jdamico.tamandare.components.URLManager;

import junit.framework.TestCase;

public class SearchTest extends TestCase {
	public void testSearch(){
		URLManager.getInstance().search("damico");
	}

}
