package org.jdamico.tamandare.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdamico.tamandare.components.ConnectionManager;
import org.jdamico.tamandare.components.LoggerManager;
import org.jdamico.tamandare.dataobjects.Combo;
import org.jdamico.tamandare.threads.ThreadRunnableManager;
import org.jdamico.tamandare.transactions.TransactionManager;

public class Connection extends HttpServlet {

	private static final long serialVersionUID = 5419096499685900304L;

	protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String host = request.getParameter("host");
		String entityName = request.getParameter("entityName");

		Combo combo = null;
		PrintWriter out = response.getWriter();

		List<String> error = new ArrayList<String>();
		if(host==null) error.add("Null host");

		if(error.size() == 0){
			try {
				combo = ConnectionManager.getInstance().isHostValid(host, entityName);
			} catch (Exception e) {
				LoggerManager.getInstance().logAtExceptionTime(this.getClass().getName(), e.getMessage());
				e.printStackTrace();
			}
		}
		if(combo!=null){
			String rMsg = combo.getXmlObj().getHeader().getMessageReturn().getReturnMsg();
			String rCode = combo.getXmlObj().getHeader().getMessageReturn().getReturnCode();
			
			int code = Integer.parseInt(rCode);
			
			if(code == 0){
				/**
				 * Save valid connections to a history table
				 */
				TransactionManager tm = new TransactionManager();
				tm.saveInHistoryConn(host, entityName);
			}
			
			out.println(ServletUtils.getInstance().jsRedirect("home?msg="+rMsg+" ("+rCode+")"));
		}
		out.close();
	}
	
	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ThreadRunnableManager.getInstance().privateNetSyncThread();
		PrintWriter out = response.getWriter();
		out.println(ServletUtils.getInstance().jsRedirect("home?msg=Sync started."));
		out.close();
	}

}
