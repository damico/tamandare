package org.jdamico.tamandare.utils;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;

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
		File schemaLocation = new File("/tmp/tamandare.xsd");
		Schema schema = null;
		try {
			schema = factory.newSchema(schemaLocation);
		} catch (SAXException e) {
			throw new TamandareException(e.getStackTrace());
		}
		Validator validator = schema.newValidator();
		Source source = new StreamSource(new StringReader(xml));
		try {
			validator.validate(source);
		} catch (SAXException e) {
			throw new TamandareException(e.getStackTrace());
		} catch (IOException e) {
			throw new TamandareException(e.getStackTrace());
		}

		return true;

	}

}
