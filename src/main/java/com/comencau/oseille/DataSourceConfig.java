package com.comencau.oseille;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * TODO
 *
 * @author Nicolas FABRE
 * @since 2018-05-01
 */
@Configuration
public class DataSourceConfig {

    @Bean(destroyMethod = "close")
    public javax.sql.DataSource datasource() {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl("jdbc:postgresql://localhost:5432/oseille");
        ds.setUsername("postgres");
        ds.setPassword("klkl");
        return ds;
    }

    @Bean
    public JdbcTemplate tpl() {
        return new JdbcTemplate(datasource());
    }

}
