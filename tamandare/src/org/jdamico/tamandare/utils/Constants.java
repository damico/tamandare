package org.jdamico.tamandare.utils;

public interface Constants {

	/*
	 * Message types
	 */
	public static final int LINK = 0;
	public static final int ERROR = 1;
	public static final int SYSMSG = 2;
	public static final int ENTITY = 3;
	public static final int TAGS = 4;
	
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


	public static final String SQL_GETDOCS_BY_TAG_DERBY = "select DOC_URL_HASH, XMLSERIALIZE(DOC_DATA " +
	"AS clob ) " +
	"FROM TAMANDARE.XMLDOCS " +
	"WHERE DOC_URL_HASH is not null and XMLEXISTS('//tamandare/body/tags[contains(translate(.,\"ABCDEFGHIJKLMNOPQRSTUVWXYZ\",\"abcdefghijklmnopqrstuvwxyz\"),\"VAR\")]' passing BY REF DOC_DATA) ";


	
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
	
	public static final String SQL_GET_XML_BY_ENTITY_NAME = "select XMLSERIALIZE(DOC_DATA " +
	"AS clob ) " +
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
	
	public static final int HTML_TITLE = 1;
	public static final int HTML_META = 2;
	public static final int HTML_INNER_URLS = 3;
	public static final int THREAD_BUSY = -2;
	public static final int HTML_RAW = -1;
	
	
	public static final String SQL_GET_URL_BY_STATUS_PG = "SELECT url FROM TAMANDARE.url WHERE status = ?";
	public static final String SQL_SAVE_URL_PG = "INSERT INTO TAMANDARE.URL (url,status)VALUES(?,?)";
	public static final String SQL_UPDATE_URL_BY_TITLE_PG = "UPDATE TAMANDARE.URL SET status = ?, title = ? WHERE url = ?";
	public static final String SQL_UPDATE_URL_BY_META_PG = "UPDATE TAMANDARE.URL SET status = ?, keywords = ? WHERE url = ?";
	public static final String SQL_UPDATE_URL_BY_STATUS_PG =  "UPDATE TAMANDARE.URL SET status = ? WHERE url = ?";
	public static final String SQL_IS_URL_STORED_PG = "SELECT status FROM TAMANDARE.URL WHERE url = ?";
	public static final String SQL_TOTAL_URL_STORED_PG = "SELECT count(url) FROM TAMANDARE.URL";
	public static final String TAMANDARE_LOG_FOLDER = "/var/log/tamandare/";
	public static final String AGENT_NET_PATH = "/tmp/agentNet.conf";
	
	public static final String APP_SCHEMA = "TAMANDARE";
	
	public static final String SQL_PREPARE_DERBY =	"create table TAMANDARE.XMLDOCS (" +
													"DOC_ID INT GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) NOT NULL PRIMARY KEY," +
													"DOC_DATA XML," +
													"DOC_URL_HASH VARCHAR(255)," +
													"DOC_TAGS_HASH VARCHAR(255)," +
													"DOC_TIMESTAMP TIMESTAMP NOT NULL DEFAULT CURRENT TIMESTAMP)";
	
	

	
	public static final String SQL_PRESESSIONS_DERBY =	"create table TAMANDARE.PRESESSIONS (" +
														"PRESESSIONS_ID INT GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) NOT NULL PRIMARY KEY," +
														"PRESESSIONS_SOURCE_ENTITY VARCHAR(255)," +
														"PRESESSIONS_TARGET_ENTITY VARCHAR(255)," +
														"PRESESSIONS_DOC_DATA XML," +
														"PRESESSIONS_TIMESTAMP TIMESTAMP NOT NULL DEFAULT CURRENT TIMESTAMP)";
	
	public static final String SQL_HISTORYCONN_DERBY =	"create table TAMANDARE.HISTORYCONN (" +
														"HISTORYCONN_ID INT GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) NOT NULL UNIQUE," +
														"HISTORYCONN_ADDR VARCHAR(255)," +
														"HISTORYCONN_ENTITY VARCHAR(255)," +
														"HISTORYCONN_TIMESTAMP TIMESTAMP NOT NULL DEFAULT CURRENT TIMESTAMP," +
														"PRIMARY KEY (HISTORYCONN_ADDR, HISTORYCONN_ENTITY))"; 
	
	public static final String[] DEFAULT_TABLES = {SQL_PREPARE_DERBY, SQL_PRESESSIONS_DERBY, SQL_HISTORYCONN_DERBY};
	public static final String[] DEFAULT_TABLES_NAMES = {"XMLDOCS", "PRESESSIONS", "HISTORYCONN"};
	
	public static final String SQL_SAVE_HISTORYCONN_DERBY = "insert into TAMANDARE.HISTORYCONN (HISTORYCONN_ADDR, HISTORYCONN_ENTITY) values (?,?)";
	
	public static final String SQL_GET_TABLES_DERBY = "select TABLENAME from  sys.systables st "
        + "left join sys.sysschemas ss on st.schemaid=ss.schemaid where schemaname=?";
	
	public static final String SQL_GET_HISTORYCONN_DERBY = "SELECT HISTORYCONN_ADDR, HISTORYCONN_ENTITY FROM TAMANDARE.HISTORYCONN";
	
	
	
	
	
	

	
	

}
