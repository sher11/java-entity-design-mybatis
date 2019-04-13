package com.sharebooks.database.sql.manager;
/**
*@author sher.mohammad
**/

import java.sql.Connection;

public interface SqlDBManager {
	Connection getConnection() throws Exception;
	public void initialize();
	public void commit();
	public void rollback();
}
