package com.comencau.oseille;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

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
    public void init() {
        jdbcTemplate.execute("DROP TABLE IF EXISTS TRANSACTION"); // to recreate the internal database, uncomment this
        jdbcTemplate.execute("CREATE TABLE transaction (id BIGINT PRIMARY KEY, date DATE NOT NULL, label VARCHAR(250) NOT NULL, comment varchar(500)," +
                " amount DECIMAL NOT NULL, creation_date timestamp(0) with time zone)");
        jdbcTemplate.execute("DROP SEQUENCE IF EXISTS transaction_s");
        jdbcTemplate.execute("CREATE SEQUENCE IF NOT EXISTS transaction_s START WITH 1");
    }

}
