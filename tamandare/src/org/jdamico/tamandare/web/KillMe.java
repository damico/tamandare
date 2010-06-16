package org.jdamico.tamandare.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdamico.tamandare.transactions.TransactionManager;

public class KillMe extends HttpServlet {
	
	private static final long serialVersionUID = -5708863213110725947L;

	protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TransactionManager tm = new TransactionManager();
		tm.addJob("killJob", 1);
	}

}
