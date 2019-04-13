package com.sharebooks.database.sql.manager;

import javax.sql.DataSource;

/**
 * Interface to be implemented by any SQL Database used by the system.
 * 
 * @author sher.mohammad
 *
 */
public interface SqlDataSource {
	DataSource dataSource();
	void initialize();
}
