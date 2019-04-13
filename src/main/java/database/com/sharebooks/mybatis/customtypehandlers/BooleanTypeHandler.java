package com.sharebooks.mybatis.customtypehandlers;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

public class BooleanTypeHandler implements TypeHandler<Boolean> {

    @Override
    public void setParameter(PreparedStatement ps, int i, Boolean parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter ? 1 : 0);
    }

    @Override
    public Boolean getResult(ResultSet rs, String columnName) throws SQLException {
        return Integer.parseInt(rs.getString(columnName))==1;
    }

    @Override
    public Boolean getResult(ResultSet rs, int columnIndex) throws SQLException {
        return Integer.parseInt(rs.getString(columnIndex))==1;
    }

    @Override
    public Boolean getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return Integer.parseInt(cs.getString(columnIndex))==1;
    }
}
