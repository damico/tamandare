package org.jdamico.tamandare.transactions;

import org.jdamico.tamandare.exceptions.TamandareException;

public class TransactionManager {

	public boolean saveDoc(String xml) throws TamandareException {
		return DB2manager.getInstance().saveDocument(xml);
	}

}
