package org.jdamico.tamandare.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Home extends HttpServlet {

	private static final long serialVersionUID = -646775933588106293L;
	
	private String bodyA = 	"<form method = 'post' action = 'postUrl'>" +
		"<table width = \"400\" align = \"center\" cellpadding = '10' cellspacing = '0'>\n"+
		"<tr valign = 'top' bgcolor='#99B8FF' valign='top'>\n"+
		"<td><b><font color='WHITE'>New</font></b></td><td><b>&nbsp</b></td>\n"+
		"</tr>\n"+
		"<tr valign = 'top' bgcolor='#EFEFEF' valign='top'>\n"+
		"<td>Url:</td><td> <input type='text' name='url'> </td>\n"+
		"</tr>\n"+
	
		"</tr>\n"+
		"<tr valign = 'top' bgcolor='#F0F0F0' valign='top'>\n"+
		"<td>Tags:</td><td> <input type='text' name='tags'> </td>\n"+
		"</tr>\n"+
		
		"</tr>\n"+
		"<tr valign = 'top' bgcolor='#EFEFEF' valign='top'>\n"+
		"<td>&nbsp</td><td> <input type='submit' name='submit'> </td>\n"+
		"</tr>\n"+
		
		"</table>" +
		"</form>" +
		"\n";
	
	private String bodyB = 	"<form method = 'post' action = 'postSearch'>" +
	"<table width = \"400\" align = \"center\" cellpadding = '10' cellspacing = '0'>\n"+
	"<tr valign = 'top' bgcolor='#99B8FF' valign='top'>\n"+
	"<td><b><font color='WHITE'> Search </font></b></td><td><b><font color='WHITE'><center>&nbsp</center></font></b></td>\n"+
	"</tr>\n"+
	"<tr valign = 'top' bgcolor='#EFEFEF' valign='top'>\n"+
	"<td>Key:</td><td> <input type='text' name='key'> </td>\n"+
	"</tr>\n"+

	"</tr>\n"+
	"<tr valign = 'top' bgcolor='#F0F0F0' valign='top'>\n"+
	"<td>&nbsp</td><td> <input type='submit' name='submit'> </td>\n"+
	"</tr>\n"+
	
	"</table>" +
	"</form>" +
	"\n";
	
	
	private String bodyC = 	"<form method = 'post' action = 'postUrl'>" +
	"<table width = \"400\" align = \"center\" cellpadding = '10' cellspacing = '0'>\n"+
	"<tr valign = 'top' bgcolor='#99B8FF' valign='top'>\n"+
	"<td><b><font color='WHITE'>Connect</font></b></td><td><b><font color='WHITE'><center>&nbsp</center></font></b></td>\n"+
	"</tr>\n"+
	"<tr valign = 'top' bgcolor='#EFEFEF' valign='top'>\n"+
	"<td>Host:</td><td> <input type='text' name='url'> </td>\n"+
	"</tr>\n"+

	"</tr>\n"+
	"<tr valign = 'top' bgcolor='#F0F0F0' valign='top'>\n"+
	"<td>&nbsp</td><td> <input type='submit' name='submit'> </td>\n"+
	"</tr>\n"+
	
	"</table>" +
	"</form>" +
	"\n";
	
	private String bodyD = 	"<form method = 'post' action = 'postUrl'>" +
	"<table width = \"400\" align = \"center\" cellpadding = '10' cellspacing = '0'>\n"+
	"<tr valign = 'top' bgcolor='#99B8FF' valign='top'>\n"+
	"<td><b><font color='WHITE'>Entity</font></b></td><td><b><div align='right'><a href='#'>manage</a></div></b></td>\n"+
	"</tr>\n"+
	"<tr valign = 'top' bgcolor='#EFEFEF' valign='top'>\n"+
	"<td>Type:</td><td> Human <input type='radio' name='type' value='human'> Machine  <input type='radio' name='type' value='machine'></td>\n"+
	"</tr>\n"+

	"</tr>\n"+
	"<tr valign = 'top' bgcolor='#F0F0F0' valign='top'>\n"+
	"<td>Signature:</td><td> <textarea name='signature'></textarea> </td>\n"+
	"</tr>\n"+
	
	"</tr>\n"+
	"<tr valign = 'top' bgcolor='#EFEFEF' valign='top'>\n"+
	"<td>&nbsp</td><td> <input type='submit' name='submit'> </td>\n"+
	"</tr>\n"+
	
	"</table>" +
	"</form>" +
	"\n";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println(ServletUtils.getInstance().getHTMLHeader("Home"));
		String msg = request.getParameter("msg");
		if(msg!=null) out.println(ServletUtils.getInstance().getHTMLalert(msg));
		out.println(ServletUtils.getInstance().getFFtable(bodyA, bodyB, bodyC, bodyD, "700"));
		out.println(ServletUtils.getInstance().getHTMLFooter());
		out.close();
	}

}
