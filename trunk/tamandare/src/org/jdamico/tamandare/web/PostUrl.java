package org.jdamico.tamandare.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdamico.tamandare.components.URLManager;
import org.jdamico.tamandare.dataobjects.Combo;
import org.jdamico.tamandare.exceptions.TamandareException;

public class PostUrl extends HttpServlet {
	
	private static final long serialVersionUID = -8680141511468595194L;

	protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Combo combo = null;
		PrintWriter out = response.getWriter();
		String url = request.getParameter("url");
		String tags = request.getParameter("tags");
		List<String> error = new ArrayList<String>();
		if(url==null) error.add("Null url");
		if(tags==null) error.add("Null tags");
		if(error.size() == 0){
			try {
				combo = URLManager.getInstance().storeURL(url, tags);
			} catch (TamandareException e) {
				e.printStackTrace();
			}
		}
		
		String ret = combo.getXmlObj().getHeader().getMessageReturn().getReturnCode();
		String rMsg = combo.getXmlObj().getHeader().getMessageReturn().getReturnMsg();
		if(ret.equals("0")){
			out.println(ServletUtils.getInstance().jsRedirect("home?msg="+rMsg));
		}
		
		out.close();
	}

}
