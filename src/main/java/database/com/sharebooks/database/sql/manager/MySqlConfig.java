package com.sharebooks.database.sql.manager;
public interface MySqlConfig extends SqlDatabaseConfig {
	String user();

	String password();

	String database();

	int size();
}