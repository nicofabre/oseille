package com.comencau.oseille;

import com.comencau.oseille.core.ImportService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImportINGTest {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private ImportService importService;

	@Test
	public void test() throws IOException {
		File input = new File(System.getProperty("user.home") + "/Desktop", "40000841581_20170101_20180326.csv");
		importService.importING(input);
	}

	@Test
	@Rollback(false)
	public void test2() {
		int update = jdbcTemplate.update("delete from transaction where id = 1");
		System.out.println("update = " + update);
	}

}
