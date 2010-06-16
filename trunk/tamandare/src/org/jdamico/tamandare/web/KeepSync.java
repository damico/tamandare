package org.jdamico.tamandare.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdamico.tamandare.transactions.TransactionManager;

public class KeepSync extends HttpServlet {
	
	private static final long serialVersionUID = -5708863213010725947L;

	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TransactionManager tm = new TransactionManager();
		tm.handleKeepSyncJob();
		PrintWriter out = response.getWriter();
		out.println(ServletUtils.getInstance().jsRedirect("home?msg=KeepSync started."));
		out.close();
	}

}
