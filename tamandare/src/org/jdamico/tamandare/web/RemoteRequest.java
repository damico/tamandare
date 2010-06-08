package org.jdamico.tamandare.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdamico.tamandare.components.Converter2ObjFactory;
import org.jdamico.tamandare.components.ObjConverters;
import org.jdamico.tamandare.components.URLManager;
import org.jdamico.tamandare.dataobjects.TamandareXMLObject;
import org.jdamico.tamandare.exceptions.TamandareException;
import org.jdamico.tamandare.utils.Constants;
import org.jdamico.tamandare.utils.TamandareHelper;

public class RemoteRequest extends HttpServlet {

	private static final long serialVersionUID = -1475727896191265637L;
	protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tags = request.getParameter("tags");
		System.err.println("=============>>>> "+tags);
		
		/* TODO:
		 * Compare tags
		 * Get the common ones
		 * Send what you have
		 * Get what you did not
		 */
		
		
		
		URLManager.getInstance().sendUrlsByTagsIntersection(tags, request.getRemoteAddr());
		
		
		
		
		
		
	}
	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

}
