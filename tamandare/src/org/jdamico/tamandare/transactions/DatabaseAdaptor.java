package org.jdamico.tamandare.transactions;

import org.jdamico.tamandare.exceptions.TamandareException;

public interface DatabaseAdaptor {
	public boolean saveDocument(String xml) throws TamandareException;
}
