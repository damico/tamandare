package org.jdamico.tamandare.transactions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.jdamico.tamandare.exceptions.TamandareException;
import org.jdamico.tamandare.utils.Constants;

public class DB2manager implements DatabaseAdaptor {

	private static final DB2manager INSTANCE = new DB2manager();
	public static DB2manager getInstance(){
		return INSTANCE;
	}
	
	public boolean saveDocument(String xml, String urlHash, String tagsHash) throws TamandareException  {
		boolean ret = false;
		PreparedStatement ps = null;
		Connection con = null;
		String classfn = "com.ibm.db2.jcc.DB2Driver";
		String dburl = "jdbc:db2://" + Constants.DBHOST + ":" + Constants.DBPORT + "/" + Constants.DBNAME + "";
		try {
			Class.forName(classfn);
			con = DriverManager.getConnection(dburl, Constants.DBUSER, Constants.DBPASSWD);
			ps = con.prepareStatement(Constants.SQL_SAVEDOC_DB2);
			ps.setString(1, xml);
			ps.executeUpdate();
			ret = true;
		} catch (SQLException e) {
			throw new TamandareException(e.getStackTrace());
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

}
