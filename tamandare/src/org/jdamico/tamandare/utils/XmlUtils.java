package org.jdamico.tamandare.utils;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.jdamico.tamandare.exceptions.TamandareException;
import org.xml.sax.SAXException;

public class XmlUtils {

	public boolean isDocValid(String xml) throws TamandareException {

		SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
		File schemaLocation = new File(Constants.XSD_PATH);

		if(schemaLocation.exists()){
			Schema schema = null;
			try {
				schema = factory.newSchema(schemaLocation);
			} catch (SAXException e) {
				e.printStackTrace();
				throw new TamandareException("Invalid xsd or xsd not found: "+e.getStackTrace(),this.getClass().getName());
			}
			Validator validator = schema.newValidator();
			
			
			
			Source source = new StreamSource(new StringReader(xml));
			String err = null;
			try {
				validator.validate(source);
			} catch (SAXException e) {
				try {
					err = URLEncoder.encode(e.getMessage(), "UTF-8");
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
				throw new TamandareException(e.getStackTrace(),err);
			} catch (IOException e) {
				try {
					err = URLEncoder.encode(e.getMessage(), "UTF-8");
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
				throw new TamandareException(e.getStackTrace(),err);
			}
		}else{
			throw new TamandareException("xsd not found!",this.getClass().getName());
		}
		
			
	
		
		

		return true;

	}

}
