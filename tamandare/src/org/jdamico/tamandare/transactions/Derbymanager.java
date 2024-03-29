package org.jdamico.tamandare.transactions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.jdamico.tamandare.components.LoggerManager;
import org.jdamico.tamandare.dataobjects.HistoryConnection;
import org.jdamico.tamandare.dataobjects.Job;
import org.jdamico.tamandare.dataobjects.TamandareBody;
import org.jdamico.tamandare.exceptions.TamandareException;
import org.jdamico.tamandare.utils.Constants;



public class Derbymanager extends DatabaseConfig implements DatabaseAdaptor {

	private static final Derbymanager INSTANCE = new Derbymanager();
	public static Derbymanager getInstance(){
		return INSTANCE;
	}

	/*
	 * xmldocs
	 * entity
	 * */

	public boolean saveDocument(String xml, String urlHash, String tagsHash) throws TamandareException  {
		boolean ret = false;
		PreparedStatement ps = null;
		Connection con = null;

		try {
			Class.forName(getClassfn());
			con = DriverManager.getConnection(getDBurl());
			ps = con.prepareStatement(Constants.SQL_SAVEDOC_DERBY);
			ps.setString(1, xml);
			ps.setString(2, urlHash);
			ps.setString(3, tagsHash);
			ps.executeUpdate();
			ret = true;
		} catch (SQLException e) {
			LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "xml: "+xml);
			e.printStackTrace();
			throw new TamandareException(e.getStackTrace(), e.getMessage());
		} catch (ClassNotFoundException e) {

			throw new TamandareException(e.getStackTrace());
		} catch (Exception e) {

			throw new TamandareException(e.getStackTrace());
		} finally {
			try {
				if(ps!=null) ps.close();
				if(con!=null) con.close();
			} catch (SQLException e) {
				throw new TamandareException(e.getStackTrace());
			}
		}

		return ret;
	}

	public Map<Integer, String> search(String key) {
		Map<Integer, String> urls = null;
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;

		try {
			Class.forName(getClassfn());
			con = DriverManager.getConnection(getDBurl());
			String sql = Constants.SQL_GETDOCS_BY_SEARCH_DERBY.replaceAll("VAR", key);
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			urls = new HashMap<Integer, String>();
			while(rs.next()){
				urls.put(rs.getInt(1), rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



		return urls;
	}



	public Map<String, String> getDocsByTag(String tag) throws TamandareException {
		Map<String, String> docs = null;
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		String sql = Constants.SQL_GETDOCS_BY_TAG_DERBY.replaceAll("VAR", tag);
		try {
			Class.forName(getClassfn());
			con = DriverManager.getConnection(getDBurl());

			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			docs = new HashMap<String, String>();
			while(rs.next()){
				docs.put(rs.getString(1), rs.getString(2));
			}
		} catch (SQLException e) {
			LoggerManager.getInstance().logAtExceptionTime(this.getClass().getName(), "SQL: "+sql+" > "+e.getMessage());

			
				System.err.println(e.getMessage());
			


		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally{
			try { if(rs!=null) rs.close(); } catch (SQLException e) { throw new TamandareException(e.getStackTrace()); }
			try { if(ps!=null) ps.close(); } catch (SQLException e) { throw new TamandareException(e.getStackTrace()); }
			try { if(con!=null) con.close(); } catch (SQLException e) { throw new TamandareException(e.getStackTrace()); }
		}



		return docs;
	}

	public ArrayList<String> getTags() throws TamandareException {
		ArrayList<String> tags = null;
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;

		try {
			Class.forName(getClassfn());
			con = DriverManager.getConnection(getDBurl());
			ps = con.prepareStatement(Constants.SQL_GETDOCS_BY_TAGS_DERBY);
			rs = ps.executeQuery();
			tags = new ArrayList<String>();
			while(rs.next()){
				String element = rs.getString(1);
				if(!tags.contains(element)){
					tags.add(element);
				}
			}
		} catch (SQLException e) {
			LoggerManager.getInstance().logAtExceptionTime(this.getClass().getName(), "SQL: "+Constants.SQL_GETDOCS_BY_TAGS_DERBY+" > "+e.getMessage());
			
				System.err.println(e.getMessage());
			
		} catch (ClassNotFoundException e) {
			throw new TamandareException(e.getStackTrace());
		}finally{
			try { if(rs!=null) rs.close(); } catch (SQLException e) { throw new TamandareException(e.getStackTrace()); }
			try { if(ps!=null) ps.close(); } catch (SQLException e) { throw new TamandareException(e.getStackTrace()); }
			try { if(con!=null) con.close(); } catch (SQLException e) { throw new TamandareException(e.getStackTrace()); }
		}
		return tags;
	}

	public boolean isURLstored(String urlHash) {

		boolean isURLstored = false;
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;

		try {
			Class.forName(getClassfn());
			con = DriverManager.getConnection(getDBurl());
			ps = con.prepareStatement(Constants.SQL_ISURL_HASH_STORED);
			ps.setString(1, urlHash);
			rs = ps.executeQuery();
			while(rs.next()){
				isURLstored = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return isURLstored;
	}

	public boolean isURLstored(TamandareBody body) throws TamandareException {
		boolean isURLstored = false;
		String sql = Constants.SQL_ISURLSTORED.replaceAll("VAR", body.getUrl());

		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;

		try {
			Class.forName(getClassfn());
			con = DriverManager.getConnection(getDBurl());
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				isURLstored = true;
			}
		} catch (SQLException e) {
			LoggerManager.getInstance().logAtExceptionTime(this.getClass().getName(), "SQL: "+sql);
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {

			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(con!=null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new TamandareException(e.getStackTrace());
			}
		}
		return isURLstored;
	}


	public void delete(int docid) {

		PreparedStatement ps = null;
		Connection con = null;

		try {
			Class.forName(getClassfn());
			con = DriverManager.getConnection(getDBurl());
			ps = con.prepareStatement(Constants.SQL_DELETE_BY_DOC_ID);
			ps.setInt(1, docid);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}


	}

	public ArrayList<String> getTagsById(int id) {
		ArrayList<String> tags = null;
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;

		try {
			Class.forName(getClassfn());
			con = DriverManager.getConnection(getDBurl());
			ps = con.prepareStatement(Constants.SQL_GETDOCS_BY_TAGS_BY_ID_DERBY);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			tags = new ArrayList<String>();
			while(rs.next()){
				tags.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
		return tags;
	}

	public String[] getDocById(int id) {
		String[] ret = new String[3];
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;

		try {
			Class.forName(getClassfn());
			con = DriverManager.getConnection(getDBurl());
			ps = con.prepareStatement(Constants.SQL_GETDOCS_BY_DOC_BY_ID_DERBY);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			while(rs.next()){
				ret[0] = rs.getString(1);
				ret[1] = rs.getString(2);
				ret[2] = rs.getString(3);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
		return ret;
	}

	public boolean update(int docId, String xml) throws TamandareException {

		boolean ret = false;

		PreparedStatement ps = null;
		Connection con = null;

		try {
			Class.forName(getClassfn());
			con = DriverManager.getConnection(getDBurl());
			ps = con.prepareStatement(Constants.SQL_UPDATE_BY_DOC_ID_BY_XML);
			ps.setInt(2, docId);
			ps.setString(1, xml);
			ps.executeUpdate();
			ret = true;
		} catch (SQLException e) {
			throw new TamandareException(e.getStackTrace());
		} catch (ClassNotFoundException e) {
			throw new TamandareException(e.getStackTrace());
		}
		return ret;
	}

	public boolean isEntitySignatureStored(String signature) {
		boolean isEntityStored = false;
		String sql = Constants.SQL_ISENTITY_SIGNATURE_STORED.replaceAll("VAR", signature);


		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;

		try {
			Class.forName(getClassfn());
			con = DriverManager.getConnection(getDBurl());
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				isEntityStored = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return isEntityStored;
	}

	public Map<Integer, String> getDocsByType(int docType, String outputPath) {
		Map<Integer, String> entities = null;
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;

		try {
			Class.forName(getClassfn());
			con = DriverManager.getConnection(getDBurl());
			String sql = Constants.SQL_GETDOCS_BY_DOCTYPE_DERBY.replaceAll("VAR1", String.valueOf(docType));
			sql = sql.replaceAll("VAR2", outputPath);


			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			entities = new HashMap<Integer, String>();
			while(rs.next()){
				entities.put(rs.getInt(1), rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



		return entities;
	}

	public String getSignature(String entityName) {
		String ret = null;
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;

		try {
			Class.forName(getClassfn());
			con = DriverManager.getConnection(getDBurl());
			ps = con.prepareStatement(Constants.SQL_GET_SIGNATURE_BY_ENTITYNAME_DERBY.replaceAll("VAR1", entityName));
			rs = ps.executeQuery();

			while(rs.next()){
				ret = rs.getString(2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
		return ret;

	}

	public boolean isEntityNameStored(String value) {
		boolean isEntityStored = false;
		String sql = Constants.SQL_ISENTITY_NAME_STORED.replaceAll("VAR", value);

		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;

		try {
			Class.forName(getClassfn());
			con = DriverManager.getConnection(getDBurl());
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				isEntityStored = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return isEntityStored;
	}

	public String getXMLbyEntityName(String entityName) throws TamandareException {
		String xml = null;
		String sql = Constants.SQL_GET_XML_BY_ENTITY_NAME.replaceAll("VAR", entityName);

		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;

		try {
			Class.forName(getClassfn());
			con = DriverManager.getConnection(getDBurl());
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				xml = rs.getString(1);
			}
		} catch (SQLException e) {
			LoggerManager.getInstance().logAtExceptionTime(this.getClass().getName(), "SQL: "+sql+" "+e.getMessage());
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {

			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(con!=null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new TamandareException(e.getStackTrace());
			}
		}
		return xml;
	}

	public ArrayList<String> getSchemas() throws TamandareException{
		String sql = "select SCHEMANAME from SYS.SYSSCHEMAS";
		ArrayList<String> schemas = new ArrayList<String>();
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;

		try {
			Class.forName(getClassfn());
			con = DriverManager.getConnection(getDBurl());
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				schemas.add(rs.getString(1));
			}
		} catch (SQLException e) {
			LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "SQL: "+sql);
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {

			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(con!=null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new TamandareException(e.getStackTrace());
			}
		}
		return schemas;
	}

	public void prepareDB(String sql) throws TamandareException {
		PreparedStatement ps = null;
		Connection con = null;

		try {
			Class.forName(getClassfn());
			con = DriverManager.getConnection(getDBurl());
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new TamandareException(e.getStackTrace(), sql+" > "+e.getMessage());
		} catch (ClassNotFoundException e) {

			throw new TamandareException(e.getStackTrace());
		} catch (Exception e) {

			throw new TamandareException(e.getStackTrace());
		} finally {
			try { if(ps!=null) ps.close(); } catch (SQLException e) { throw new TamandareException(e.getStackTrace()); }
			try { if(con!=null) con.close(); } catch (SQLException e) { throw new TamandareException(e.getStackTrace()); }
		}

	}

	public void saveInHistoryConn(String host, String entityName) throws TamandareException, java.sql.SQLIntegrityConstraintViolationException {
		PreparedStatement ps = null;
		Connection con = null;

		try {
			Class.forName(getClassfn());
			con = DriverManager.getConnection(getDBurl());
			ps = con.prepareStatement(Constants.SQL_SAVE_HISTORYCONN_DERBY);
			ps.setString(1, host);
			ps.setString(2, entityName);
			ps.executeUpdate();
		
		} catch (SQLException e) {
			throw new TamandareException(e.getStackTrace(), e.getMessage());
		} catch (ClassNotFoundException e) {

			throw new TamandareException(e.getStackTrace());
		} catch (Exception e) {

			throw new TamandareException(e.getStackTrace());
		} finally {

			try { if(ps!=null) ps.close(); } catch (SQLException e) { throw new TamandareException(e.getStackTrace()); }
			try { if(con!=null) con.close(); } catch (SQLException e) { throw new TamandareException(e.getStackTrace()); }

		}

	}

	public ArrayList<String> getExistentTables() throws TamandareException {

		ArrayList<String> existentTables = new ArrayList<String>(Constants.DEFAULT_TABLES.length);

		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;

		try {
			Class.forName(getClassfn());
			con = DriverManager.getConnection(getDBurl());
			ps = con.prepareStatement(Constants.SQL_GET_TABLES_DERBY);
			ps.setString(1, Constants.APP_SCHEMA);
			rs = ps.executeQuery();
			while(rs.next()){
				existentTables.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new TamandareException(e.getStackTrace(), e.getMessage());
		} catch (ClassNotFoundException e) {

			throw new TamandareException(e.getStackTrace());
		} catch (Exception e) {

			throw new TamandareException(e.getStackTrace());
		} finally {
			try { if(rs!=null) rs.close(); } catch (SQLException e) { throw new TamandareException(e.getStackTrace()); }
			try { if(ps!=null) ps.close(); } catch (SQLException e) { throw new TamandareException(e.getStackTrace()); }
			try { if(con!=null) con.close(); } catch (SQLException e) { throw new TamandareException(e.getStackTrace()); }
		}
		return existentTables;
	}

	public ArrayList<HistoryConnection> getHistoryConn() throws TamandareException {
		ArrayList<HistoryConnection> ret = new ArrayList<HistoryConnection>();

		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;

		try {
			Class.forName(getClassfn());
			con = DriverManager.getConnection(getDBurl());
			ps = con.prepareStatement(Constants.SQL_GET_HISTORYCONN_DERBY);
			rs = ps.executeQuery();
			while(rs.next()){
				ret.add(new HistoryConnection(rs.getString(1), rs.getString(2)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new TamandareException(e.getStackTrace(), e.getMessage());
		} catch (ClassNotFoundException e) {
			throw new TamandareException(e.getStackTrace());
		} catch (Exception e) {
			throw new TamandareException(e.getStackTrace());
		} finally {
			try { if(rs!=null) rs.close(); } catch (SQLException e) { throw new TamandareException(e.getStackTrace()); }
			try { if(ps!=null) ps.close(); } catch (SQLException e) { throw new TamandareException(e.getStackTrace()); }
			try { if(con!=null) con.close(); } catch (SQLException e) { throw new TamandareException(e.getStackTrace()); }
		}
		return ret;
	}

	public ArrayList<Job> getJobs() throws TamandareException {
		ArrayList<Job> ret = new ArrayList<Job>();

		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;

		try {
			Class.forName(getClassfn());
			con = DriverManager.getConnection(getDBurl());
			ps = con.prepareStatement(Constants.SQL_GET_JOBS_DERBY);
			rs = ps.executeQuery();
			while(rs.next()){
				 Timestamp cDate = rs.getTimestamp(4);
				
				ret.add(new Job(rs.getInt(1), rs.getString(2), rs.getInt(3), new java.util.Date(cDate.getTime()) ));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new TamandareException(e.getStackTrace(), e.getMessage());
		} catch (ClassNotFoundException e) {
			throw new TamandareException(e.getStackTrace());
		} catch (Exception e) {
			throw new TamandareException(e.getStackTrace());
		} finally {
			try { if(rs!=null) rs.close(); } catch (SQLException e) { throw new TamandareException(e.getStackTrace()); }
			try { if(ps!=null) ps.close(); } catch (SQLException e) { throw new TamandareException(e.getStackTrace()); }
			try { if(con!=null) con.close(); } catch (SQLException e) { throw new TamandareException(e.getStackTrace()); }
		}
		return ret;
	}

	public boolean isKeepSyncJob() throws TamandareException {
		boolean ret = false;

		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;

		try {
			Class.forName(getClassfn());
			con = DriverManager.getConnection(getDBurl());
			ps = con.prepareStatement(Constants.SQL_ISKEEPSYNC_DERBY);
			ps.setString(1, "privateNetSyncThread");
			rs = ps.executeQuery();
			while(rs.next()){
				ret = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new TamandareException(e.getStackTrace(), e.getMessage());
		} catch (ClassNotFoundException e) {
			throw new TamandareException(e.getStackTrace());
		} catch (Exception e) {
			throw new TamandareException(e.getStackTrace());
		} finally {
			try { if(rs!=null) rs.close(); } catch (SQLException e) { throw new TamandareException(e.getStackTrace()); }
			try { if(ps!=null) ps.close(); } catch (SQLException e) { throw new TamandareException(e.getStackTrace()); }
			try { if(con!=null) con.close(); } catch (SQLException e) { throw new TamandareException(e.getStackTrace()); }
		}
		return ret;
	}

	public void deleteJobByName(String jobName) throws TamandareException {
		PreparedStatement ps = null;
		Connection con = null;

		try {
			Class.forName(getClassfn());
			con = DriverManager.getConnection(getDBurl());
			ps = con.prepareStatement(Constants.SQL_DELETE_JOB_BY_NAME);
			ps.setString(1, jobName);
			ps.executeUpdate();
		
		} catch (SQLException e) {
			throw new TamandareException(e.getStackTrace(), e.getMessage());
		} catch (ClassNotFoundException e) {

			throw new TamandareException(e.getStackTrace());
		} catch (Exception e) {

			throw new TamandareException(e.getStackTrace());
		} finally {

			try { if(ps!=null) ps.close(); } catch (SQLException e) { throw new TamandareException(e.getStackTrace()); }
			try { if(con!=null) con.close(); } catch (SQLException e) { throw new TamandareException(e.getStackTrace()); }

		}
		
	}

	public void addJob(String jobName, int interval) throws TamandareException {
		PreparedStatement ps = null;
		Connection con = null;

		try {
			Class.forName(getClassfn());
			con = DriverManager.getConnection(getDBurl());
			ps = con.prepareStatement(Constants.SQL_ADD_JOB_DERBY);
			ps.setString(1, jobName);
			ps.setInt(2, interval);
			ps.executeUpdate();
		
		} catch (SQLException e) {
			throw new TamandareException(e.getStackTrace(), e.getMessage());
		} catch (ClassNotFoundException e) {

			throw new TamandareException(e.getStackTrace());
		} catch (Exception e) {

			throw new TamandareException(e.getStackTrace());
		} finally {

			try { if(ps!=null) ps.close(); } catch (SQLException e) { throw new TamandareException(e.getStackTrace()); }
			try { if(con!=null) con.close(); } catch (SQLException e) { throw new TamandareException(e.getStackTrace()); }

		}
		
	}

	public void updateKeepSync(String last) throws TamandareException {
		PreparedStatement ps = null;
		Connection con = null;

		try {
			Class.forName(getClassfn());
			con = DriverManager.getConnection(getDBurl());
			ps = con.prepareStatement(Constants.SQL_UPDATEKEEPSYNC_DERBY);
			ps.setString(2, "privateNetSyncThread");
			ps.setString(1, last);
			ps.executeUpdate();
		
		} catch (SQLException e) {
			throw new TamandareException(e.getStackTrace(), e.getMessage());
		} catch (ClassNotFoundException e) {

			throw new TamandareException(e.getStackTrace());
		} catch (Exception e) {

			throw new TamandareException(e.getStackTrace());
		} finally {

			try { if(ps!=null) ps.close(); } catch (SQLException e) { throw new TamandareException(e.getStackTrace()); }
			try { if(con!=null) con.close(); } catch (SQLException e) { throw new TamandareException(e.getStackTrace()); }

		}
	}

	
}
