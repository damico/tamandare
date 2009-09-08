package org.jdamico.tamandare.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdamico.tamandare.components.EntityManager;
import org.jdamico.tamandare.dataobjects.Combo;
import org.jdamico.tamandare.exceptions.TamandareException;

public class Entity  extends HttpServlet {
	
	private static final long serialVersionUID = 8781286139549107706L;

	protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Combo combo = null;
		PrintWriter out = response.getWriter();
		String type = request.getParameter("type");
		String signature = request.getParameter("signature");
		List<String> error = new ArrayList<String>();
		if(type==null) error.add("Null type");
		if(signature==null) error.add("Null signature");
		if(error.size() == 0){
			try {
				combo = EntityManager.getInstance().storeEntity(type, signature);
			} catch (TamandareException e) {
				e.printStackTrace();
			}
		}
		
		String ret = combo.getXmlObj().getHeader().getMessageReturn().getReturnCode();
		String rMsg = combo.getXmlObj().getHeader().getMessageReturn().getReturnMsg();
		out.println(ServletUtils.getInstance().jsRedirect("home?msg="+rMsg));
		out.close();
	}
}
