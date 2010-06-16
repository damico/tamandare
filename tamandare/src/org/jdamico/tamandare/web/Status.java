package org.jdamico.tamandare.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdamico.tamandare.utils.Constants;

public class Status extends HttpServlet {

	private static final long serialVersionUID = 522722940206437074L;

	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.println("{\"version\": \""+Constants.APP_VERSION+"\"}");
		out.close();
	}
}
