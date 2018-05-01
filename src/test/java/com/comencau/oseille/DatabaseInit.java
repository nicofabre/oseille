package com.comencau.oseille;

import com.comencau.oseille.core.Transaction;
import com.comencau.oseille.utils.DaoUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author Nicolas FABRE
 * @since 2018-03-28
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DatabaseInit {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    @Rollback(false)
    public void init() {
        jdbcTemplate.execute("DROP TABLE IF EXISTS TRANSACTION"); // to recreate the internal database, uncomment this
        jdbcTemplate.execute("CREATE TABLE transaction (id BIGINT PRIMARY KEY, date DATE NOT NULL, label VARCHAR(250) NOT NULL, comment varchar(500)," +
                " amount DECIMAL NOT NULL, creation_date timestamp)");
        jdbcTemplate.execute("DROP SEQUENCE IF EXISTS transaction_s");
        jdbcTemplate.execute("CREATE SEQUENCE IF NOT EXISTS transaction_s START WITH 1");

        jdbcTemplate.query("show tables from PUBLIC", rs -> {
            while (rs.next()) {
                System.out.println("rs.getString(1) = " + rs.getString(1));
            }
            return null;
        });

        List<Transaction> transactions = new ArrayList<>();

        Transaction tr = new Transaction();
        tr.setId(1L);
        tr.setDate(LocalDate.now());
        tr.setLabel("lalalala");
        tr.setComment("cocococ");
        tr.setAmount(new BigDecimal(24));
        transactions.add(tr);

        OffsetDateTime now = OffsetDateTime.now();
        jdbcTemplate.batchUpdate("INSERT INTO transaction (id, date, label, comment, amount, creation_date) VALUES (?,?,?,?,?,?)", transactions, 1000, (ps, tx) -> {
            ps.setLong(1, tx.getId());
            ps.setDate(2, DaoUtils.toSqlDate(tx.getDate()));
            ps.setString(3, tx.getLabel());
            ps.setString(4, tx.getComment());
            ps.setBigDecimal(5, tx.getAmount());
            ps.setObject(6, now);
        });

    }

    @Test
    public void test() {
//        jdbcTemplate.query("select * from public.transaction", rs -> {
//        jdbcTemplate.query("show databases", rs -> {
//        rs.getString(1) = INFORMATION_SCHEMA
//        rs.getString(1) = PUBLIC
        jdbcTemplate.query("show tables from PUBLIC", rs -> {
            while (rs.next()) {
                System.out.println("rs.getString(1) = " + rs.getString(1));
            }
            return null;
        });
    }

}
