package org.jdamico.tamandare.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdamico.tamandare.components.EntityManager;
import org.jdamico.tamandare.components.LiveMemoryManager;
import org.jdamico.tamandare.components.URLManager;
import org.jdamico.tamandare.utils.ManageProperties;

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
	
	
	private String bodyC = 	"<form method = 'post' action = 'initConn'>" +
	"<table width = \"400\" align = \"center\" cellpadding = '10' cellspacing = '0'>\n"+
	"<tr valign = 'top' bgcolor='#99B8FF' valign='top'>\n"+
	"<td><b><font color='WHITE'>Connect</font></b></td><td><b><font color='WHITE'><center>&nbsp</center></font></b></td>\n"+
	"</tr>\n"+
	"<tr valign = 'top' bgcolor='#EFEFEF' valign='top'>\n"+
	"<td>Host:</td><td>###SELECT###@<input type='text' size='16' name='host'> </td>\n"+
	"</tr>\n"+

	"</tr>\n"+
	"<tr valign = 'top' bgcolor='#F0F0F0' valign='top'>\n"+
	"<td>&nbsp</td><td> <input type='submit' name='submit'> </td>\n"+
	"</tr>\n"+
	
	"</table>" +
	"</form>" +
	"\n";
	
	private String bodyD = 	"<form method = 'post' action = 'postEntity'>" +
	"<table width = \"400\" align = \"center\" cellpadding = '10' cellspacing = '0'>\n"+
	"<tr valign = 'top' bgcolor='#99B8FF' valign='top'>\n"+
	"<td><b><font color='WHITE'>Entity</font></b></td><td><b><div align='right'><a href='listEntity'>manage</a></div></b></td>\n"+
	"</tr>\n"+
	"<tr valign = 'top' bgcolor='#EFEFEF' valign='top'>\n"+
	"<td>Type:</td><td> Human <input type='radio' name='type' value='human' checked> Machine  <input type='radio' name='type' value='machine'></td>\n"+
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
	
		Map<String, String> tagsMap = new HashMap<String, String>();
		
		Map<Integer, String> entities = EntityManager.getInstance().getEntityNames();
		Set<Integer> eSet = entities.keySet();
		Iterator<Integer> it = eSet.iterator();
		StringBuffer sb = new StringBuffer();
		sb.append("<select name='entityName'>");
		
		while(it.hasNext()){
			int eKey = it.next();
			String eVal = entities.get(eKey);
			sb.append("<option>"+eVal+"</option>");
		}

		sb.append("</select>");
		
		
		
		StringBuffer m = new StringBuffer();
		
		ArrayList<String> tags = URLManager.getInstance().getTags();
		for(int i = 0; i < tags.size(); i++){
			m.append(ServletUtils.getInstance().cleanTags(tags.get(i)));
		}
		
		StringBuffer t = new StringBuffer();
		
		StringTokenizer st = new StringTokenizer(m.toString(), " ");
		while(st.hasMoreTokens()){
			String e = (String) st.nextElement();	
			tagsMap.put(e, "<a href='postSearch?key="+e+"'>"+e+"</a> ");
		}
		
		
		Collection<String> tagsCol = tagsMap.values();
		
		Iterator<String> i = tagsCol.iterator();
		
		while(i.hasNext()){
			t.append(i.next());
		}
		
		
		Map<String, Boolean> sessions = LiveMemoryManager.getSessions();
		Set<String> keys = sessions.keySet();
		
		Object[] keysArray = keys.toArray();
		
		
		
		
		String bodyE = 	"<table width='100' border='0' align='center' cellpadding='8' cellspacing='8'><tr><td>" +
				"<table width = \"250\" align = \"center\" cellpadding = '10' cellspacing = '0'>\n"+
		"<tr valign = 'top' bgcolor='#99B8FF' valign='top'>\n"+
		"<td><b><font color='WHITE'>Tags</font></b></td>\n"+
		"</tr>\n"+
		"<tr valign = 'top' bgcolor='#EFEFEF' valign='top'>\n"+
		"<td>"+t.toString()+"</td>\n"+
		"</tr>\n"+
		"</table>" +
		"</td></tr></table>" +
		"\n";
		
		PrintWriter out = response.getWriter();
		out.println(ServletUtils.getInstance().getHTMLHeader("Home of "+ManageProperties.getInstance().read("whoami")));
		String msg = request.getParameter("msg");
		if(msg!=null) out.println(ServletUtils.getInstance().getHTMLalert(msg));
		String staticScreen = "<table width='90%' border='0' align='center' cellpadding='2' cellspacing='2'>" +
				"<tr valign='top'><td>"+ServletUtils.getInstance().getFFtable(bodyA, bodyB, bodyC, bodyD, "500")+"</td><td>"+bodyE+"</td></tr>" +
				"</table>";
		
		staticScreen = staticScreen.replaceAll("###SELECT###", sb.toString());
		
		out.println(staticScreen);
		
		out.println(ServletUtils.getInstance().getHTMLFooter());
		out.close();
	}

}
