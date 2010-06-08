package org.jdamico.tamandare.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdamico.tamandare.components.LoggerManager;
import org.jdamico.tamandare.components.URLManager;

public class RemoteRequest extends HttpServlet {

	private static final long serialVersionUID = -1475727896191265637L;
	protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tags = request.getParameter("tags");
		String doc = request.getParameter("doc");
		
		LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(),"RECEIVED TAGS =============>>>> "+tags);
		LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(),"RECEIVED DOC =============>>>> "+doc);
		
		/* TODO:
		 * Calls bellow should be threads since beginning
		 */
		
		
		if(tags!=null){
			URLManager.getInstance().sendUrlsByTagsIntersection(tags, request.getRemoteAddr());
		}else if(doc!=null){
			URLManager.getInstance().saveDocByXml(doc);
		}else{
			LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "No valid parameter from remote agent");
		}
		
		
		
		
		
		
		
	}
	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

}
