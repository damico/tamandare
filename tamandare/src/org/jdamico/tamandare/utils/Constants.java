package org.jdamico.tamandare.utils;

public interface Constants {

	/*
	 * Message types
	 */
	public static final int LINK = 0;
	public static final int ERROR = 1;
	public static final int SYSMSG = 2;
	public static final int ENTITY = 3;
	
	public static final String DBPORT = "50000";
	public static final String DBHOST = "127.0.0.1";
	public static final String DBNAME = "amit";
	public static final String DBPASSWD = "aptiva10p";
	public static final String DBUSER = "jdamico";
	
	/*
	 * SQLs
	 */
	
	public static final String SQL_SAVEDOC_DB2 = "insert into TAMANDARE.XMLDOCS (DOC_DATA, DOC_URL_HASH, DOC_TAGS_HASH) values (?,?,?)";
	public static final String SQL_SAVEDOC_DERBY = "insert into TAMANDARE.XMLDOCS (DOC_DATA, DOC_URL_HASH, DOC_TAGS_HASH) values ( XMLPARSE(DOCUMENT CAST (? AS CLOB) PRESERVE WHITESPACE), ?, ? )";
	public static final String SQL_GETDOCS_BY_SEARCH_DERBY = "select DOC_ID, XMLSERIALIZE(xmlquery('string(//tamandare/body/url/@value)' passing BY REF DOC_DATA EMPTY ON EMPTY) " +
																"AS clob) " +
																"FROM TAMANDARE.XMLDOCS " +
																"WHERE XMLEXISTS('//tamandare/body/tags[contains(translate(.,\"ABCDEFGHIJKLMNOPQRSTUVWXYZ\",\"abcdefghijklmnopqrstuvwxyz\"),\"VAR\")]' passing BY REF DOC_DATA) ";

	public static final String SQL_GETDOCS_BY_DOCTYPE_DERBY = "select DOC_ID, XMLSERIALIZE(xmlquery('string(VAR2)' passing BY REF DOC_DATA EMPTY ON EMPTY) " +
	"AS clob) " +
	"FROM TAMANDARE.XMLDOCS " +
	"WHERE XMLEXISTS('//tamandare/header[@type = \"VAR1\"]' passing BY REF DOC_DATA) ";


	public static final String SQL_GET_SIGNATURE_BY_ENTITYNAME_DERBY = "select DOC_ID, XMLSERIALIZE(xmlquery('string(//tamandare/body/signature/content)' passing BY REF DOC_DATA EMPTY ON EMPTY) " +
	"AS clob) " +
	"FROM TAMANDARE.XMLDOCS " +
	"WHERE XMLEXISTS('//tamandare/body/signature[@entity = \"VAR1\"]' passing BY REF DOC_DATA) ";
	
	
	public static final String SQL_GETDOCS_BY_TAGS_DERBY = "select XMLSERIALIZE(xmlquery('//tamandare/body/tags' passing BY REF DOC_DATA EMPTY ON EMPTY) " +
	"AS clob) " +
	"FROM TAMANDARE.XMLDOCS ";

	public static final String SQL_GETDOCS_BY_TAGS_BY_ID_DERBY = "select XMLSERIALIZE(xmlquery('//tamandare/body/tags' passing BY REF DOC_DATA EMPTY ON EMPTY) " +
	"AS clob) " +
	"FROM TAMANDARE.XMLDOCS WHERE DOC_ID = ?";
	
	
	public static final String SQL_GETDOCS_BY_DOC_BY_ID_DERBY = "select DOC_URL_HASH, DOC_TAGS_HASH, XMLSERIALIZE(DOC_DATA " +
	"AS clob ) " +
	"FROM TAMANDARE.XMLDOCS WHERE DOC_ID = ?";
	
	public static final String SQL_ISURLSTORED = "select XMLSERIALIZE(xmlquery('//tamandare/body/url' passing BY REF DOC_DATA EMPTY ON EMPTY) " +
	"AS clob) " +
	"FROM TAMANDARE.XMLDOCS " +
	"WHERE XMLEXISTS ('//tamandare/body/url[@value = \"VAR\"]' passing BY REF DOC_DATA) ";
	
	public static final String SQL_ISURL_HASH_STORED = "SELECT DOC_URL_HASH FROM TAMANDARE.XMLDOCS WHERE DOC_URL_HASH = ?";
	
	public static final String SQL_ISENTITY_SIGNATURE_STORED = "select DOC_ID FROM TAMANDARE.XMLDOCS " +
	"WHERE XMLEXISTS ('//tamandare/body/signature/content[text()=\"VAR\"]' passing BY REF DOC_DATA) ";
	
	public static final String SQL_DELETE_BY_DOC_ID = "DELETE FROM TAMANDARE.XMLDOCS WHERE DOC_ID = ?";
	
	public static final String SQL_UPDATE_BY_DOC_ID_BY_XML = "UPDATE TAMANDARE.XMLDOCS SET DOC_DATA = XMLPARSE(DOCUMENT CAST (? AS CLOB) PRESERVE WHITESPACE) WHERE DOC_ID = ?";
	
	public static final String SQL_ISENTITY_NAME_STORED = "select XMLSERIALIZE(xmlquery('//tamandare/body/signature' passing BY REF DOC_DATA EMPTY ON EMPTY) " +
	"AS clob) " +
	"FROM TAMANDARE.XMLDOCS " +
	"WHERE XMLEXISTS ('//tamandare/body/signature[@entity = \"VAR\"]' passing BY REF DOC_DATA) ";
	
	
	/* App data */
	
	public static final String XSD_PATH = "/etc/tamandare/tamandare.xsd";
	
	public static final int WEB_SERVER_PORT = 8989;
	public static final int SOCKET_SERVER_PORT = 8888;
	
	public static final String APP_NAME = "Tamandare";
	public static final String APP_VERSION = "0.0.1";
	
	/* conf file */
	public static final String PROPERTIES_PATH = "/etc/tamandare/tamandare.conf";
	
	
	public static final String DB_CLASSFN = "classfn";
	public static final String DB_PATH = "dbpath";
	public static final String WHO_AM_I = "whoami";
	public static final String TYPE = "type";
	public static final String MY_ADDR = "myaddr";
	
	/* sys messages */
	public static final String URL_FINE_ADDED = "Url added!";
	public static final String ENTITY_FINE_ADDED = "Entity added!";
	public static final String URL_ALREADY_ADDED = "Url already added!";
	public static final String ENTITY_ALREADY_ADDED = "Entity already added!";

	
	
	public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	
	
	public static final String DEFAULT_HASH_TYPE = "SHA-512";
	
	public static final String SIGNATURE_DELIMITER = ":|:";
	
	public static final String LOG_NAME = "tamandare.log";
	public static final String TAMANDARE_FOLDER = "/home/jdamico/.tamandare/";
	public static final String ENTITIES_CACHE = TAMANDARE_FOLDER+"cache";
	public static final String SEVERE_LOGLEVEL = " S ";
	public static final String NORMAL_LOGLEVEL = " N ";
	public static final int FIXED_LOGLIMIT = 5000000;
	
	
	
	
	
	
	
	
	
	
	
	

	
	

}
