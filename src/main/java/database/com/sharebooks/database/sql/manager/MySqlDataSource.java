package com.sharebooks.database.sql.manager;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public enum MySqlDataSource implements SqlDataSource {
	BOOK_DB(DefaultMySqlConfig.BOOK_DB);
	private final String host;
	private final int port;
	private final String user;
	private final String password;
	private final String database;
	private final int size;
	private DataSource dataSource;

	private MySqlDataSource(MySqlConfig config) {
		host = config.host();
		port = config.port();
		user = config.user();
		password = config.password();
		database = config.database();
		size = config.size();

	}

	public static synchronized boolean intialize() {
		try {
			for (MySqlDataSource manager : MySqlDataSource.values()) {
				manager.initialize();
			}
			return true;
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public void initialize() {
		try {
			HikariConfig config = new HikariConfig();
			String jdbcUrl = "jdbc:mysql://" + host + ":" + port + "/" + database+"?useSSL=false";
			config.setJdbcUrl(jdbcUrl);
			config.setUsername(user);
			config.setPassword(password);
			config.setMaximumPoolSize(size);
			// Optional Settings
			/* 120 seconds is wait_timeout on our servers. */
			config.setLeakDetectionThreshold(120 * 1000);
			config.setIdleTimeout(30 * 1000);
			config.addDataSourceProperty("cachePrepStmts", "true");
			config.addDataSourceProperty("prepStmtCacheSize", "250");
			config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
			dataSource = new HikariDataSource(config);

		} catch (Exception e) {
			throw new RuntimeException("FATAL: Error intializing Database Manager: " + name(), e);
		}
	}

	@Override
	public DataSource dataSource() {
		return dataSource;
	}

}