package com.comencau.oseille.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

/**
 * TODO
 *
 * @author Nicolas FABRE
 * @since 2018-03-28
 */
public class DaoUtils {

    // Pour les ResultSet
    public static LocalDate toLocalDate(java.sql.Date sqlDate) {
        if (sqlDate == null) {
            return null;
        } else {
            return Instant.ofEpochMilli(sqlDate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        }
    }

    public static LocalDate getLocalDate(ResultSet resultSet, String columnLabel) throws SQLException {
        return toLocalDate(resultSet.getDate(columnLabel));
    }

    // Pour les PreparedStatement
    public static java.sql.Date toSqlDate(LocalDate localDate) {
        return new java.sql.Date(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
    }
    public static void setLocalDate(PreparedStatement preparedStatement, int parameterIndex, LocalDate localDate) throws SQLException {
        if (localDate == null) {
            preparedStatement.setNull(parameterIndex, Types.DATE);
        } else {
            preparedStatement.setDate(parameterIndex, toSqlDate(localDate));
        }
    }

}
