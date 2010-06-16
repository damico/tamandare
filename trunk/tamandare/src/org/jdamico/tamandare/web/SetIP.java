package org.jdamico.tamandare.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdamico.tamandare.components.LoggerManager;
import org.jdamico.tamandare.utils.Constants;
import org.jdamico.tamandare.utils.ManageProperties;

public class SetIP extends HttpServlet {
	private static final long serialVersionUID = -4283018999834932327L;

	protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String myip = request.getParameter("myip");
		if(myip!=null){
			ManageProperties.getInstance().setProp(Constants.AGENT_NET_PATH, Constants.MY_ADDR, (String) myip);
			LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "MY_ADDR: "+ManageProperties.getInstance().read(Constants.AGENT_NET_PATH, Constants.MY_ADDR));
		}
		PrintWriter out = response.getWriter();
		out.println(ServletUtils.getInstance().jsRedirect("home?msg=Network changed."));
		out.close();
	}


}
