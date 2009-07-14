package org.jdamico.tamandare.transactions;

import org.jdamico.tamandare.utils.Constants;
import org.jdamico.tamandare.utils.ManageProperties;

public class DatabaseConfig {
	public String getClassfn(){
		return ManageProperties.getInstance().read(Constants.DB_CLASSFN);
	}
	
	public String getDBurl(){
		String dburl = "jdbc:derby:" + ManageProperties.getInstance().read(Constants.DB_PATH) + ";create=true;";
		return dburl;
	}
}
