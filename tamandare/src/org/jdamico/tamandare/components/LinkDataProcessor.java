package org.jdamico.tamandare.components;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.jdamico.tamandare.dataobjects.LinkObject;
import org.jdamico.tamandare.dataobjects.TamandareBody;
import org.jdamico.tamandare.dataobjects.TamandareHeader;
import org.jdamico.tamandare.dataobjects.TamandareReturn;
import org.jdamico.tamandare.dataobjects.TamandareXMLObject;
import org.jdamico.tamandare.utils.Constants;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/*
 * <?xml version="1.0" encoding="ISO-8859-1"?>
<tamandare xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation="tamandare.xsd">
<header date="2009-08-13 17:10:33" type ="0" >
<return code ="0" message="success" />
</header>
<body>
<url value="http://dcon.com.br" />
<tags>blog</tags>
<tags>damico</tags>
</body>
</tamandare>
 */

public class LinkDataProcessor extends DefaultHandler implements DefaultDataProcessor{

	/*
	 * (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	
	private TamandareXMLObject message = new LinkObject();
	private TamandareHeader header = new TamandareHeader();
	private TamandareBody body = new TamandareBody();
	private TamandareReturn lreturn = new TamandareReturn(0, "success");
	private ArrayList<String> tagArrayList = new ArrayList<String>();
	//private Vector alertVector = new Vector();
	
	private StringBuffer buffer = null;
	private boolean isTagActive = false;
	private String activeTag = null;

	public static final String TAG_HEADER = "header";
	public static final String TAG_URL = "url";
	public static final String TAG_BODY = "body";
	public static final String TAG_TAGS = "tags";
	public static final String TAG_SIGNATURES = "signature";
	
	
public void startElement (String namespaceUri, String localName, String qualifiedName, Attributes attributes) {
	
		
		if(qualifiedName.equals(TAG_HEADER)){
			activeTag = TAG_HEADER;
			
			DateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT);
	        Date date = null;
			try {
				date = (Date)formatter.parse(attributes.getValue("date"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			header.setDate(date);
			header.setType(0);
			header.setMessageReturn(lreturn);
			isTagActive = true;
		}else if(qualifiedName.equals(TAG_BODY)){
			activeTag = TAG_BODY;
			isTagActive = true;
			
		}else if(qualifiedName.equals(TAG_URL)){
			activeTag = TAG_URL;
			body.setUrl(attributes.getValue("value"));
			isTagActive = true;
			
		}else if(qualifiedName.equals(TAG_TAGS)){
			activeTag = TAG_TAGS;
			isTagActive = true;
			
		}else if(qualifiedName.equals(TAG_SIGNATURES)){
			activeTag = TAG_SIGNATURES;
			body.setEntity(attributes.getValue("entity"));
			isTagActive = true;
			
		}else{
			isTagActive = false;
			buffer = null;
		}

	}
	
	public void characters(char[] chars,int start,int length){
		
       if(isTagActive && activeTag.equals(TAG_TAGS)){
    	   if(buffer==null) buffer = new StringBuffer();
           buffer.append(chars,start,length);

       }else{
			buffer = null;
       }
  
    }
	
	public void endElement(String uri, String name, String qualifiedName){

		if(qualifiedName.equals(TAG_TAGS)){ 
			isTagActive = true;
			if(buffer!=null){ /* get content */ 
				tagArrayList.add(buffer.toString());
			}
			buffer = null;
		} 
		
	}
	

	@Override
	public TamandareXMLObject getData() {
		message.setHeader(header);
		String[] tagArray = new String[tagArrayList.size()];
		for(int i = 0; i < tagArrayList.size(); i++){
			tagArray[i] = tagArrayList.get(i);
		}
		
		body.setTags(tagArray);
		message.setBody(body);
		return message;
	}

}
