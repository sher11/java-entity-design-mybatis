package com.sharebooks.database.sql.manager;

public enum DataBaseConigFile {
	BOOK_DB("bookdb.properties");

	String fileName;

	DataBaseConigFile(String fileName) {
		this.fileName = fileName;
	}

	public String fileName() {
		return fileName;
	}

	/**
	 * HOST=127.0.01 
	 * PORT=3306 
	 * USER=root 
	 * PASSWORD=root 
	 * DATABASE=book 
	 * SIZE=20
	 **/
}