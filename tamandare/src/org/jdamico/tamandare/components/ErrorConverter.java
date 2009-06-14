package org.jdamico.tamandare.components;

import java.text.Format;
import java.text.SimpleDateFormat;

import org.jdamico.tamandare.dataobjects.TamandareXMLObject;
import org.jdamico.tamandare.exceptions.TamandareException;
import org.jdamico.tamandare.utils.XmlUtils;

public class ErrorConverter implements Converters {
	
	TamandareXMLObject xmlObj;

	public ErrorConverter(TamandareXMLObject xmlObj) {
		this.xmlObj = xmlObj;
	}

	public String exec() throws TamandareException {
		Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strDate = formatter.format(xmlObj.getHeader().getDate());
		String xml = 	"<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n" +
						"<tamandare xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"tamandare.xsd\">\n" +
						"<header date=\""+ strDate +"\" type =\""+xmlObj.getHeader().getType()+"\" >\n" +
						"<return code =\""+xmlObj.getHeader().getMessageReturn().getReturnCode()+"\" message=\""+xmlObj.getHeader().getMessageReturn().getReturnMsg()+"\" />\n" +
						"</header>\n" +
						"<body/>\n" +
						"</tamandare>";
		
		XmlUtils xmlUtils = new XmlUtils();
		xmlUtils.isDocValid(xml);
		
		return xml;
	}

}
