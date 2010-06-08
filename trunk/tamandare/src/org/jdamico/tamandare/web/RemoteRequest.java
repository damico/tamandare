package org.jdamico.tamandare.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemoteRequest extends HttpServlet {

	private static final long serialVersionUID = -1475727896191265637L;
	protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tags = request.getParameter("tags");
		System.err.println("=============>>>> "+tags);
	}
	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

}
