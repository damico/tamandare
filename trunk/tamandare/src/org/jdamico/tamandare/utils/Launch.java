package org.jdamico.tamandare.utils;

import org.jdamico.tamandare.web.JettyController;

public class Launch {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		JettyController jController = new JettyController();
		jController.init();

	}

}
