package com.sharebooks.mybatis.customtypehandlers;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

/**
 * @author sher.mohammad
 **/
@MappedTypes(UUID.class)
public class UUIDTypeHanlder extends BaseTypeHandler<UUID> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, UUID parameter, JdbcType jdbcType)
			throws SQLException {
		ps.setBytes(i, UUIDHelper.toBytes(parameter));
	}

	@Override
	public UUID getNullableResult(ResultSet rs, String columnName) throws SQLException {
		byte[] s = rs.getBytes(columnName);
		UUID uid = UUIDHelper.fromBytes(s);
		return uid;
	}

	@Override
	public UUID getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		byte[] s = rs.getBytes(columnIndex);
		UUID uid = UUIDHelper.fromBytes(s);
		return uid;
	}

	@Override
	public UUID getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		byte[] s = cs.getBytes(columnIndex);
		UUID uid = UUIDHelper.fromBytes(s);
		return uid;
	}

}
