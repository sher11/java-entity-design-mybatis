package com.sharebooks.rest.Book;
/**
*@author sher.mohammad
**/

import org.mybatis.dynamic.sql.SqlColumn;

public interface TableDescription<X extends Entity> {
	<T> SqlColumn<T> myBatisColumn();
}
