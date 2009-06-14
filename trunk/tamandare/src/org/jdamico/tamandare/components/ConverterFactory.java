package org.jdamico.tamandare.components;

import org.jdamico.tamandare.dataobjects.TamandareXMLObject;
import org.jdamico.tamandare.utils.Constants;

public class ConverterFactory {
	public static Converters getConverter(int type, TamandareXMLObject xmlObj){
		switch(type){
		case Constants.LINK:
			return new LinkConverter(xmlObj);
		case Constants.ERROR:
			return new ErrorConverter(xmlObj);
		default:
			return null;
		}
	}
}
