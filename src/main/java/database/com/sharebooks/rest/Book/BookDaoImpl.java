package com.sharebooks.rest.Book;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.render.RenderingStrategy;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;

public class BookDaoImpl implements BookDao {

	@Override
	public List<Book> selectAllBooks(SqlSession session) {
		RowBounds rowbounds = new RowBounds(10, 100);
		BookMapper mapper = session.getMapper(BookMapper.class);
		SelectStatementProvider sqlstmt = SqlBuilder.select(BookMapper.BookTableColumns.allColumns())
				.from(BookMapper.sqlTable).build().render(RenderingStrategy.MYBATIS3);
		return mapper.selectManyWithRowbounds(sqlstmt, rowbounds);
	}

	@Override
	public void insertBook(SqlSession session, Book book) {
		BookMapper mapper = session.getMapper(BookMapper.class);
		mapper.insert(book);

	}

	@Override
	public void insertBook(SqlSession session, List<Book> books) {
		for (Book book : books) {
			insertBook(session, book);
		}

	}

	@Override
	public Book selectBookById(SqlSession session, long id) {
		BookMapper mapper = session.getMapper(BookMapper.class);
		SelectStatementProvider sqlstmt = SqlBuilder.select(BookMapper.BookTableColumns.allColumns())
				.from(BookMapper.sqlTable)
				.where(BookMapper.BookTableColumns.ID.myBatisColumn(), SqlBuilder.isEqualTo(id)).build()
				.render(RenderingStrategy.MYBATIS3);

		return mapper.selectOne(sqlstmt);

	}

}
