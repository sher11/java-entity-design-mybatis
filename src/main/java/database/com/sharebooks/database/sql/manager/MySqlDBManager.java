package com.sharebooks.database.sql.manager;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.SqlSessionManager;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

/**
 * @author sher.mohammad
 **/
public enum MySqlDBManager implements SqlDBManager {
	BOOK_DB(MySqlDataSource.BOOK_DB, "com.knowledgeking.rest");
	private SqlSessionManager sessionManager;

	public static synchronized boolean intialize() {
		try {
			for (MySqlDBManager manager : MySqlDBManager.values()) {
				manager.initialize();
			}
			return true;
		} catch (RuntimeException e) {
			throw e;
		}
	}

	MySqlDBManager(SqlDataSource dataSource, String mappersPackageName) {
		this.dataSource = dataSource.dataSource();
		this.mappersPackageName = mappersPackageName;
	}

	DataSource dataSource;
	String mappersPackageName;

	public Connection getConnection() throws SQLException {
		return sessionManager.getConnection();
	}

	public SqlSessionManager sessionManager() {
		return sessionManager;
	}

	@Override
	public void rollback() {
		sessionManager.rollback();
	}

	@Override
	public void commit() {
		sessionManager.commit();
	}

	@Override
	public void initialize() {
		DataSource dataSource = this.dataSource;
		TransactionFactory trxFactory = new JdbcTransactionFactory();
		Environment env = new Environment("local", trxFactory, dataSource);
		Configuration config = new Configuration(env);
		config.addMappers(this.mappersPackageName, LocalMapper.class);
		config.getTypeHandlerRegistry().register("com.knowledgeking.mybatis");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(config);
		sessionManager = SqlSessionManager.newInstance(factory);
	}
}
