package com.comencau.oseille;

import com.comencau.oseille.core.ImportService;
import com.comencau.oseille.core.Transaction;
import com.comencau.oseille.core.TransactionRowMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImportINGTest {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private ImportService importService;

	@Test
	public void test() throws IOException {
		File input = new File(System.getProperty("user.home") + "/Desktop/input");
		for (File file : input.listFiles()) {
			System.out.println("file = " + file);
			importService.importING(file);
		}
	}

	@Test
	public void testSelect() {
		Long cnt = jdbcTemplate.queryForObject("select count(*) from transaction", Long.class);
		System.out.println("cnt = " + cnt);
	}

	@Test
	public void testSelect2() {
		Double cnt = jdbcTemplate.queryForObject("select sum(amount) from transaction", Double.class);
		System.out.println("cnt = " + cnt);
	}

}
