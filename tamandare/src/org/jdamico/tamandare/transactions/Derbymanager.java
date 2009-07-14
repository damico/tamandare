package org.jdamico.tamandare.transactions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
		
	public boolean saveDocument(String xml) throws TamandareException  {
		boolean ret = false;
		PreparedStatement ps = null;
		Connection con = null;
	
		try {
			Class.forName(getClassfn());
			con = DriverManager.getConnection(getDBurl());
			ps = con.prepareStatement(Constants.SQL_SAVEDOC);
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

	public void search(String key) {
		boolean ret = false;
		PreparedStatement ps = null;
		Connection con = null;

		try {
			Class.forName(getClassfn());
			con = DriverManager.getConnection(getDBurl());
			ps = con.prepareStatement(Constants.SQL_SAVEDOC);
//			ps.setString(1, xml);
			ps.executeUpdate();
		} catch (SQLException e) {
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
