package com.comencau.oseille.core;

import com.comencau.oseille.utils.DaoUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * TODO
 *
 * @author Nicolas FABRE
 * @since 2018-03-28
 */
public class TransactionRowMapper implements RowMapper<Transaction> {

    @Nullable
    @Override
    public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
        Transaction tx = new Transaction();
        tx.setId(rs.getLong("id"));
        tx.setDate(DaoUtils.getLocalDate(rs, "date"));
        tx.setAmount(rs.getBigDecimal("amount"));
        tx.setLabel(rs.getString("label"));
        return tx;
    }

}
