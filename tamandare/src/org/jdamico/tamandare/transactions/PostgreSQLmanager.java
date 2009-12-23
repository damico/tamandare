package org.jdamico.tamandare.transactions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.jdamico.tamandare.exceptions.TamandareException;
import org.jdamico.tamandare.utils.Constants;

public class PostgreSQLmanager {
	private static final PostgreSQLmanager INSTANCE = new PostgreSQLmanager();
	public static PostgreSQLmanager getInstance(){
		return INSTANCE;
	}
	
	
	/* 
	 * url, status
	 */
	private String DBHOST = "localhost";
	private String DBPORT = "5432";
	private String DBNAME = "tamandare";
	private String DBUSER = "tamandare";
	private String DBPASSWD = "32329954";
	
	public void saveUrl(String url, int status) throws TamandareException{

		PreparedStatement ps = null;
		Connection con = null;
		String classfn = "org.postgresql.Driver";
		String dburl = "jdbc:postgresql://" + DBHOST + ":" + DBPORT + "/" + DBNAME + "";
		try {
			Class.forName(classfn);
			con = DriverManager.getConnection(dburl, DBUSER, DBPASSWD);
			ps = con.prepareStatement(Constants.SQL_SAVE_URL_PG);
			ps.setString(1, url);
			ps.setInt(2,status);
			ps.executeUpdate();

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
	}
	
	public boolean isUrlStored(String urlStr) throws TamandareException {
		boolean ret =  false;
		PreparedStatement ps = null;
		Connection con = null;
		String classfn = "org.postgresql.Driver";
		String dburl = "jdbc:postgresql://" + DBHOST + ":" + DBPORT + "/" + DBNAME + "";
		ResultSet rs = null;
		try {
			Class.forName(classfn);
			con = DriverManager.getConnection(dburl, DBUSER, DBPASSWD);
			ps = con.prepareStatement(Constants.SQL_IS_URL_STORED_PG);
			ps.setString(1, urlStr);
			rs = ps.executeQuery();
			while(rs.next()){
				ret = true;
			}

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


	public int getUrlTotal() throws TamandareException {
		
		int ret = 0;
		PreparedStatement ps = null;
		Connection con = null;
		String classfn = "org.postgresql.Driver";
		String dburl = "jdbc:postgresql://" + DBHOST + ":" + DBPORT + "/" + DBNAME + "";
		ResultSet rs = null;
		try {
			Class.forName(classfn);
			con = DriverManager.getConnection(dburl, DBUSER, DBPASSWD);
			ps = con.prepareStatement(Constants.SQL_TOTAL_URL_STORED_PG);
			rs = ps.executeQuery();
			while(rs.next()){
				ret = rs.getInt(1);
			}

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


	public void updateUrl(String urlStr, String content, int status) throws TamandareException {
		
		String sql = null;
		if(status==Constants.HTML_TITLE) sql = Constants.SQL_UPDATE_URL_BY_TITLE_PG;
		else if(status==Constants.HTML_META) sql = Constants.SQL_UPDATE_URL_BY_META_PG;
		
		PreparedStatement ps = null;
		Connection con = null;
		String classfn = "org.postgresql.Driver";
		String dburl = "jdbc:postgresql://" + DBHOST + ":" + DBPORT + "/" + DBNAME + "";
		try {
			Class.forName(classfn);
			con = DriverManager.getConnection(dburl, DBUSER, DBPASSWD);
			ps = con.prepareStatement(sql);
			ps.setString(3, urlStr);
			ps.setString(2, content);
			ps.setInt(1,status);
			ps.executeUpdate();

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
		
	}


	public void updateUrl(String urlStr, int status) throws TamandareException {
		PreparedStatement ps = null;
		Connection con = null;
		String classfn = "org.postgresql.Driver";
		String dburl = "jdbc:postgresql://" + DBHOST + ":" + DBPORT + "/" + DBNAME + "";
		try {
			Class.forName(classfn);
			con = DriverManager.getConnection(dburl, DBUSER, DBPASSWD);
			ps = con.prepareStatement(Constants.SQL_UPDATE_URL_BY_STATUS_PG);
			ps.setString(2, urlStr);
			ps.setInt(1,status);
			ps.executeUpdate();

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
		
	}


	public ArrayList<String> getUrlArrayByStatus(int status) throws TamandareException {
		
		ArrayList<String> urlArray = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		String classfn = "org.postgresql.Driver";
		String dburl = "jdbc:postgresql://" + DBHOST + ":" + DBPORT + "/" + DBNAME + "";
		try {
			Class.forName(classfn);
			con = DriverManager.getConnection(dburl, DBUSER, DBPASSWD);
			ps = con.prepareStatement(Constants.SQL_GET_URL_BY_STATUS_PG);
			ps.setInt(1, status);
		
			rs = ps.executeQuery();
			urlArray = new ArrayList<String>();
			while(rs.next()){
				urlArray.add(rs.getString(1));
			}
		} catch (SQLException e) {
			System.err.println(status+" | "+Constants.SQL_GET_URL_BY_STATUS_PG);
			e.printStackTrace();
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
		return urlArray;
	}
}
