package com.sharebooks.rest.Book;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

public interface BookDao {
	List<Book> selectAllBooks(SqlSession session);
	
	Book selectBookById(SqlSession session, long Id);

	
	void insertBook(SqlSession session, Book book);
	
	void insertBook(SqlSession session, List<Book> book);

	
}
