package com.sharebooks.database.sql.manager;

import java.io.FileInputStream;
import java.util.Properties;

public enum DefaultMySqlConfig implements MySqlConfig {
	BOOK_DB(DataBaseConigFile.BOOK_DB.fileName());

	private final String host;
	private final int port;
	private final String user;
	private final String password;
	private final String database;
	private final int size;

	private DefaultMySqlConfig(String fileName) {
		try {
			System.out.println("fileName " +fileName);
			String filePath = DataBaseInitializer.CONFIG_LOCATION+ fileName;
			System.out.println(filePath);
			FileInputStream fis = new FileInputStream(filePath);
			Properties prop = new Properties();
			prop.load(fis);
			host = prop.getProperty("HOST");
			port = Integer.parseInt(prop.getProperty("PORT"));
			user = prop.getProperty("USER");
			password = prop.getProperty("PASSWORD");
			database = prop.getProperty("DATABASE");
			size = Integer.parseInt(prop.getProperty("SIZE"));
		} catch (Exception e) {
			throw new RuntimeException("Error initializing Database Config: " + name());
		}
	}

	@Override
	public String host() {
		return host;
	}

	@Override
	public int port() {
		return port;
	}

	@Override
	public String user() {
		return user;
	}

	@Override
	public String password() {
		return password;
	}

	@Override
	public String database() {
		return database;
	}

	@Override
	public int size() {
		return size;
	}
}