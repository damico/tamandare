package org.jdamico.tamandare.transactions;

import org.jdamico.tamandare.exceptions.TamandareException;

public class TransactionManager {

	public boolean saveDoc(String xml) throws TamandareException {
		return Derbymanager.getInstance().saveDocument(xml);
	}

	public void search(String key) {
		Derbymanager.getInstance().search(key);
	}

}
