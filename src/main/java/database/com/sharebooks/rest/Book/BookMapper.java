package com.sharebooks.rest.Book;

import java.sql.JDBCType;
import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.ByteTypeHandler;
import org.apache.ibatis.type.IntegerTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.LocalDateTimeTypeHandler;
import org.apache.ibatis.type.LongTypeHandler;
import org.apache.ibatis.type.StringTypeHandler;
import org.apache.ibatis.type.TypeHandler;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;
import org.mybatis.dynamic.sql.insert.InsertDSL;
import org.mybatis.dynamic.sql.insert.render.BatchInsert;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.render.RenderingStrategy;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;

import com.sharebooks.database.sql.manager.LocalMapper;
import com.sharebooks.mybatis.customtypehandlers.UUIDTypeHanlder;

/**
 * @author sher.mohammad
 **/

@Mapper
public interface BookMapper extends LocalMapper {
	public static final String tableName = "BookMapper";
	SqlTable sqlTable = BookTableColumns.table;

	@InsertProvider(type = SqlProviderAdapter.class, method = "insert")
	@Options(useGeneratedKeys = true, keyProperty = "record.id")
	int insert(InsertStatementProvider<Book> batchInsert);

	@InsertProvider(type = SqlProviderAdapter.class, method = "insert")
	@Options(useGeneratedKeys = true, keyProperty = "record.id")
	int batchInsert(BatchInsert<BookMapper> batchInsert);

	@UpdateProvider(type = SqlProviderAdapter.class, method = "update")
	int update(UpdateStatementProvider updateStatement);

	@SelectProvider(type = SqlProviderAdapter.class, method = "select")
	@Results(id = "resultMapping", value = {
			@Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true, typeHandler = LongTypeHandler.class),
			@Result(column = "uid", property = "uid", jdbcType = JdbcType.BINARY, id = false, typeHandler = UUIDTypeHanlder.class),
			@Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR, id = false, typeHandler = StringTypeHandler.class),
			@Result(column = "age", property = "age", jdbcType = JdbcType.INTEGER, id = false, typeHandler = IntegerTypeHandler.class),
			@Result(column = "status", property = "status", jdbcType = JdbcType.TINYINT, id = false, typeHandler = ByteTypeHandler.class),
			@Result(column = "creation_time", property = "creationTime", jdbcType = JdbcType.JAVA_OBJECT, id = false, typeHandler = LocalDateTimeTypeHandler.class),
			@Result(column = "created_by", property = "createdBy", jdbcType = JdbcType.BIGINT, id = false, typeHandler = LongTypeHandler.class),
			@Result(column = "last_modification_time", property = "lastModificationTime", jdbcType = JdbcType.JAVA_OBJECT, id = false, typeHandler = LocalDateTimeTypeHandler.class),
			@Result(column = "last_modified_by", property = "lastModifiedBy", jdbcType = JdbcType.BIGINT, id = false, typeHandler = LongTypeHandler.class)

	})
	Book selectOne(SelectStatementProvider selectStatement);

	@SelectProvider(type = SqlProviderAdapter.class, method = "select")
	@ResultMap("resultMapping")
	List<Book> selectMany(SelectStatementProvider selectStatement);

	@SelectProvider(type = SqlProviderAdapter.class, method = "select")
	@ResultMap("SimpleTableResult")
	List<Book> selectManyWithRowbounds(SelectStatementProvider selectStatement, RowBounds rowBounds);

	@Select({ "${selectStatement}", "LIMIT #{parameters.limit} OFFSET #{parameters.offset}" })
	@ResultMap("resultMapping")
	List<Book> selectByExampleWithLimitAndOffset(SelectStatementProvider selectStatement);

	default int insert(Book book) {
		InsertDSL<Book> builder = SqlBuilder.insert(book).into(sqlTable);
		for (BookTableColumns col : BookTableColumns.values()) {
			builder.map(col.myBatisColumn()).toProperty(col.javaFieldName());
		}
		return insert(builder.build().render(RenderingStrategy.MYBATIS3));
	}

	public static enum BookTableColumns implements TableDescription<Book> {
		ID("id", "id", JDBCType.BIGINT, LongTypeHandler.class),
		UID("uid", "uid", JDBCType.BINARY, UUIDTypeHanlder.class),
		NAME("name", "name", JDBCType.VARCHAR, StringTypeHandler.class),
		AGE("age", "age", JDBCType.INTEGER, IntegerTypeHandler.class),
		CREATION_TIME("creation_time", "creationTime", JDBCType.JAVA_OBJECT, LocalDateTimeTypeHandler.class),
		CREATED_BY("created_by", "createdBy", JDBCType.BIGINT, LongTypeHandler.class),
		LAST_MODIFICATION_TIME("last_modification_time", "lastModificationTime", JDBCType.JAVA_OBJECT,
				LocalDateTimeTypeHandler.class),
		LAST_MODIFIED_BY("last_modified_by", "lastModifiedBy", JDBCType.BIGINT, LongTypeHandler.class);

		public static MyBatisTable table = new MyBatisTable();
		String javaFieldName;
		String sqlColumnName;
		JDBCType type;
		Class<?> typeHandler;

		BookTableColumns(String sqlColumnName, String javaFieldName, JDBCType type,
				Class<? extends TypeHandler<?>> typeHandler) {
			this.javaFieldName = javaFieldName;
			this.sqlColumnName = sqlColumnName;
			this.type = type;
			this.typeHandler = typeHandler;
		}

		public String javaFieldName() {
			return javaFieldName;
		}

		public String sqlColumnName() {
			return sqlColumnName;
		}

		public static SqlTable table() {
			return table;
		}

		public <T> SqlColumn<T> myBatisColumn() {
			SqlColumn<T> column = SqlColumn.of(this.sqlColumnName(), table, type);
			if (typeHandler != null) {
				column = column.withTypeHandler(typeHandler.getCanonicalName());
			}
			return column;
		}

		public static BasicColumn[] allColumns() {
			BookTableColumns[] a = BookTableColumns.values();
			BasicColumn[] columns = new BasicColumn[a.length];
			int i = 0;
			for (BookTableColumns table : BookTableColumns.values()) {
				columns[i++] = table.myBatisColumn();
			}
			return columns;
		}

	}

	public static final class MyBatisTable extends SqlTable {
		public MyBatisTable() {
			super(tableName);
		}
	}

}