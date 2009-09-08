package org.jdamico.tamandare.components;

import org.jdamico.tamandare.utils.Constants;


public class Converter2ObjFactory {
	public static ObjConverters getConverter(int type, String xml){
		switch(type){
		case Constants.LINK:
			return new LinkObjConverter(xml);
		default:
			return null;
		}
	}

}
