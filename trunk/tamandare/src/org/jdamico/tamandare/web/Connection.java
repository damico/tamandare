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
import org.jdamico.tamandare.dataobjects.Combo;

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
				e.printStackTrace();
			}
		}
		if(combo!=null){
			
		
		//String ret = combo.getXmlObj().getHeader().getMessageReturn().getReturnCode();
		String rMsg = combo.getXmlObj().getHeader().getMessageReturn().getReturnMsg();
		out.println(ServletUtils.getInstance().jsRedirect("home?msg="+rMsg));
		}
		out.close();
	}
}
