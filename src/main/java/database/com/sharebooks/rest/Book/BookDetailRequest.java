package com.sharebooks.rest.Book;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sharebooks.database.sql.manager.MySqlDBManager;

/**
 * @author sher.mohammad
 **/

public class BookDetailRequest {
	private long bookId;
	private static Logger logger = LoggerFactory.getLogger(BookDetailRequest.class);

	protected BookDetailRequest() {

	}

	public Book process() {
		SqlSession session = MySqlDBManager.BOOK_DB.sessionManager().openSession(true);
		Book book = null;
		try {
			BookDao dao = new BookDaoImpl();
			book = dao.selectBookById(session, bookId);
		} catch (Exception e) {
			logger.info("Exception in processing ", e);
		} finally {
			session.close();
		}
		return book;
	}

}
