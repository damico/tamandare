package org.jdamico.tamandare.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdamico.tamandare.components.URLManager;
import org.jdamico.tamandare.dataobjects.Combo;
import org.jdamico.tamandare.exceptions.TamandareException;

public class Delete extends HttpServlet {

	private static final long serialVersionUID = -3621336094202171369L;

	protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Combo combo = new Combo();
		String rMsg = null;
		
		PrintWriter out = response.getWriter();
		String sDelId = request.getParameter("del");
		int iDelId = 0;
		try{
			iDelId = Integer.parseInt(sDelId);
		}catch(NumberFormatException nfe){
			combo = URLManager.getInstance().setErrorXML(new TamandareException("Invalid id"), combo);
			rMsg = combo.getXmlObj().getHeader().getMessageReturn().getReturnMsg();
			out.println(ServletUtils.getInstance().jsRedirect("home?msg="+rMsg));
		}
		
		
		
		if(iDelId > 0){
			combo = URLManager.getInstance().delete(iDelId);
			rMsg = combo.getXmlObj().getHeader().getMessageReturn().getReturnMsg();
			out.println(ServletUtils.getInstance().jsRedirect("home?msg="+rMsg));
		}else{
			combo = URLManager.getInstance().setErrorXML(new TamandareException("You must check the item to be deleted!"), combo);
			rMsg = combo.getXmlObj().getHeader().getMessageReturn().getReturnMsg();
			out.println(ServletUtils.getInstance().jsRedirect("home?msg="+rMsg));
		}
		
		
		
		out.close();
	}

}
