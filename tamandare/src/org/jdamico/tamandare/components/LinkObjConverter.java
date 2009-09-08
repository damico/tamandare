package org.jdamico.tamandare.components;

import java.io.IOException;

import org.jdamico.tamandare.dataobjects.TamandareXMLObject;
import org.jdamico.tamandare.exceptions.TamandareException;
import org.xml.sax.SAXException;

public class LinkObjConverter implements ObjConverters {

	private String xml;
	
	public LinkObjConverter(String xml) {
		this.xml = xml;
	}

	@Override
	public TamandareXMLObject exec() throws TamandareException {
		Parser parser = null;
		TamandareXMLObject tObj =null;
		try {
			parser = new Parser(xml);
			LinkDataProcessor dp = parser.getRawLinktData();
			tObj = dp.getData();
		} catch (SAXException e) {

			
			
			throw new TamandareException(e.getStackTrace());
			
		} catch (IOException e) {

			throw new TamandareException(e.getStackTrace());
		}
		
		return tObj;
	}

}
