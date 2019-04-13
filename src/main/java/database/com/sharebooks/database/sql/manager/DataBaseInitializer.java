package com.sharebooks.database.sql.manager;

import org.slf4j.Logger;

/**
 * @author sher.mohammad
 **/
public class DataBaseInitializer {
	public static final String CONFIG_LOCATION ="your_project_config_location_path";//ex /resources/

	private static Logger logger;
	public static final void initialize() throws Exception {
		boolean success = true;
		success= success&&MySqlDataSource.intialize();
		success= success&&MySqlDBManager.intialize();
	}

}
