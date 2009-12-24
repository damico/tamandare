package org.jdamico.tamandare.components;

import org.jdamico.tamandare.dataobjects.TamandareXMLObject;
import org.jdamico.tamandare.utils.Constants;

public class Converter2XMLFactory {
	public static XMLConverters getConverter(int type, TamandareXMLObject xmlObj){
		switch(type){
		
		case Constants.ENTITY:
			return new EntityConverter(xmlObj);
		case Constants.LINK:
			return new LinkConverter(xmlObj);
		case Constants.ERROR:
			return new ErrorConverter(xmlObj);
		case Constants.SYSMSG:
			return new SysMsgConverter(xmlObj);
		case Constants.TAGS:
			return new TagsMsgConverter(xmlObj);
		default:
			return null;
		}
	}
}
