package org.jdamico.tamandare.components;

import java.text.Format;
import java.text.SimpleDateFormat;

import org.jdamico.tamandare.dataobjects.TamandareXMLObject;
import org.jdamico.tamandare.exceptions.TamandareException;
import org.jdamico.tamandare.utils.Constants;
import org.jdamico.tamandare.utils.TamandareHelper;
import org.jdamico.tamandare.utils.XmlUtils;

public class TagsMsgConverter implements XMLConverters {
	TamandareXMLObject xmlObj;
	public TagsMsgConverter(TamandareXMLObject xmlObj) {
		this.xmlObj = xmlObj;
	}

	@Override
	public String exec() throws TamandareException {
		Format formatter = new SimpleDateFormat(Constants.DATE_FORMAT);

		TamandareHelper tm =  new TamandareHelper();

		String[] arr = xmlObj.getBody().getTags();


		String tags = tm.tagsArray2String(arr, false);

		String strDate = formatter.format(xmlObj.getHeader().getDate());
		String xml = 	"<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n" +
		"<tamandare xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"tamandare.xsd\">\n" +
		"<header date=\""+ strDate +"\" type =\""+xmlObj.getHeader().getType()+"\" >\n" +
		"<return code =\""+xmlObj.getHeader().getMessageReturn().getReturnCode()+"\" message=\""+xmlObj.getHeader().getMessageReturn().getReturnMsg()+"\" />\n" +
		"</header>\n" +
		"<body>\n" +
		tags +
		"</body>\n" +
		"</tamandare>";


		XmlUtils xmlUtils = new XmlUtils();
		xmlUtils.isDocValid(xml);



		return xml;
	}

}
