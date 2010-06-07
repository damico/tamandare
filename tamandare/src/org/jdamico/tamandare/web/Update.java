package org.jdamico.tamandare.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdamico.tamandare.components.URLManager;
import org.jdamico.tamandare.dataobjects.Combo;
import org.jdamico.tamandare.dataobjects.TamandareXMLObject;
import org.jdamico.tamandare.exceptions.TamandareException;

public class Update extends HttpServlet {

	private static final long serialVersionUID = 1710020692204590879L;

	private String tagsUpdateForm = "<form method = 'post' action = 'u'>"
			+ "<table width = \"400\" align = \"center\" cellpadding = '10' cellspacing = '0'>\n"
			+ "<tr valign = 'top' bgcolor='#99B8FF' valign='top'>\n"
			+ "<td><b><font color='WHITE'>Update</font></b></td><td><b>&nbsp</b></td>\n"
			+

			"<tr valign = 'top' bgcolor='#EFEFEF' valign='top'>\n"
			+ "<td>Url:</td><td> ###URL### </td>\n"
			+ "</tr>\n"
			+

			"</tr>\n"
			+ "<tr valign = 'top' bgcolor='#F0F0F0' valign='top'>\n"
			+ "<td>Tags:</td><td> <textarea rows='10' cols='30' name='tags'>###EDITTAGS###</textarea> " +
			" <input type = 'hidden' name = 'tagsHash' value = '###URLHASH###'>\n" +
			" <input type = 'hidden' name = 'stringDocId' value = '###STRINGDOCID###'>\n" +
			" <input type = 'hidden' name = 'xml' value = '###XML###'>\n" +
			" </td>\n"
			+ "</tr>\n" +
			
			"</tr>\n" + "<tr valign = 'top' bgcolor='#EFEFEF' valign='top'>\n"
			+ "<td>&nbsp</td><td> <input type='submit' name='submit'> </td>\n"
			+ "</tr>\n" +

			"</table>" + "</form>" + "\n";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Combo combo = new Combo();
		String rMsg = null;

		PrintWriter out = response.getWriter();

		out.println(ServletUtils.getInstance().getHTMLHeader("Update tags"));

		String sId = request.getParameter("id");
		int intId = 0;
		try {
			intId = Integer.parseInt(sId);
		} catch (NumberFormatException nfe) {
			combo = URLManager.getInstance().setErrorXML(
					new TamandareException("Invalid id"), combo);
			rMsg = combo.getXmlObj().getHeader().getMessageReturn()
					.getReturnMsg();
			out.println(ServletUtils.getInstance().jsRedirect(
					"home?msg=" + rMsg));
		}

		if (intId > 0) {

			try {
				combo = URLManager.getInstance().getDocById(intId);
			} catch (TamandareException e) {
				e.printStackTrace();
			}

			TamandareXMLObject tObj = combo.getXmlObj();

			String[] arrayTags = tObj.getBody().getTags();

			StringBuffer sb = new StringBuffer();
			String element = null;
			for (int i = 0; i < arrayTags.length; i++) {
				
				element = arrayTags[i].replaceAll("\n", "");
				sb.append(element + " ");
			}

			String ret = tagsUpdateForm.replaceAll("###EDITTAGS###", sb.toString());
			ret = ret.replaceAll("###URLHASH###", combo.getTagsHash());
			ret = ret.replaceAll("###STRINGDOCID###", String.valueOf(combo.getDocId()));
			ret = ret.replaceAll("###XML###", combo.getXml());
			
			
			
			String url = URLDecoder.decode(tObj.getBody().getUrl(), "UTF-8");

			out.println(ret.replaceAll("###URL### ", url));

		}		
		
		out.println(ServletUtils.getInstance().getHTMLFooter());

		out.close();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String tagsHash = request.getParameter("tagsHash");
		String stringDocId = request.getParameter("stringDocId");
		String xml = request.getParameter("xml");
		
		Combo combo = null;
		PrintWriter out = response.getWriter();

		String tags = request.getParameter("tags");
		List<String> error = new ArrayList<String>();

		if(tags==null) error.add("Null tags");
		if(error.size() == 0){
			combo = URLManager.getInstance().updateURL(tagsHash, tags, stringDocId, xml);
		}
		
		String ret = combo.getXmlObj().getHeader().getMessageReturn().getReturnCode();
		String rMsg = combo.getXmlObj().getHeader().getMessageReturn().getReturnMsg();
		out.println(ServletUtils.getInstance().jsRedirect("home?msg="+rMsg));
		out.close();
	}
}
