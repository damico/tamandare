package org.jdamico.tamandare.components;

import org.jdamico.tamandare.dataobjects.TamandareXMLObject;
import org.jdamico.tamandare.exceptions.TamandareException;

public interface ObjConverters {
	public TamandareXMLObject exec() throws TamandareException;
}
