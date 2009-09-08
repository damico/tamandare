package org.jdamico.tamandare.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdamico.tamandare.components.EntityManager;

public class ListEntity extends HttpServlet {
	
	private static final long serialVersionUID = 6666480661801708718L;

	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String key = request.getParameter("key");
		
		PrintWriter out = response.getWriter();
		out.println(ServletUtils.getInstance().getHTMLHeader("Manage Entities"));
		Map<Integer, String> entities = EntityManager.getInstance().getEntities();
		
		out.println("<b>Results:</b><br><br>");
		
		out.println("<table width='95%' border = '0' cellpadding = '8' cellspacing = '8'>");
		
		 Set<Integer> eSet = entities.keySet();
		 
		
		//Collection<String> urlCol = urls.values();
		Iterator<Integer> i = eSet.iterator();
		
		while(i.hasNext()){
			int eKey = i.next();
			String eVal = entities.get(eKey);
			String bgcolor = "#EFEFEF";
			eVal = URLDecoder.decode(eVal, "UTF-8");
			out.println("<tr width='99%' valign = 'top' bgcolor='"+bgcolor+"'><td>"+eVal+"</td>   <td><div align='right'> " +
						"<form action='d' method='post'>" +
						"<input type='checkbox' value='"+eKey+"' name='del'>" +
						"<input type='submit' value='d' name='submit'>" +
						"</form> </div></td></tr>");

		}
		/*
		for(int i = 0; i < urls.size(); i++){
			String bgcolor = "#EFEFEF";
			if(i % 2 == 0) bgcolor = "#F0F0F0";
			String url = URLDecoder.decode(urls.get(i), "UTF-8");
			out.println("<tr valign = 'top' bgcolor='"+bgcolor+"'><td><a href='"+url+"' target='_blank'>"+url+"</a></td>   <td><div align='right'>[<a href='u?id="+url+"' target='_blank'>u</a> | <a href='d?id="+url+"' target='_blank'>x</a>]</div></td></tr>");
		}
		*/
		
		out.println("</table>");
				
		out.println(ServletUtils.getInstance().getHTMLFooter());
		out.close();
	}
	

	
}
