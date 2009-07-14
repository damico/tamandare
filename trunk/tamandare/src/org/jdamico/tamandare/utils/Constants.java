package org.jdamico.tamandare.utils;

public interface Constants {

	public static final int LINK = 0;
	public static final int ERROR = 1;
	
	public static final String DBPORT = "50000";
	public static final String DBHOST = "127.0.0.1";
	public static final String DBNAME = "amit";
	public static final String DBPASSWD = "aptiva10p";
	public static final String DBUSER = "jdamico";
	public static final String SQL_SAVEDOC = "insert into TAMANDARE.XMLDOCS (DOC_DATA) values (?)";
	
	public static final String XSD_PATH = "/home/jdamico/workspace/tamandare/src/org/jdamico/tamandare/tests/tamandare.xsd";
	public static final int WEB_SERVER_PORT = 8989;
	public static final String APP_NAME = "Tamandare";
	public static final String APP_VERSION = "0.0.1";
	
	/* conf file */
	public static final String PROPERTIES_PATH = "/etc/tamandare/tamandare.conf";
	public static final String DB_CLASSFN = "classfn";
	public static final String DB_PATH = "dbpath";
	
	/* sys messages */
	public static final String FINE_ADDED = "Url added!";
	
	

	
	

}
