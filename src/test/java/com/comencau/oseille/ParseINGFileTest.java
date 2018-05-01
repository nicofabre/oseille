package com.comencau.oseille;

import com.comencau.oseille.core.ImportReport;
import com.comencau.oseille.core.ImportService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParseINGFileTest {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private ImportService importService;

	@Test
	public void test() throws IOException {
		File inputDir = new File("D:\\Sync\\Documents\\Banque\\extract ING");
        File f = new File(inputDir, "40000841581_20161231_20170201.csv");
        ImportReport report = importService.parseINGFile(f);
        System.out.println("report = " + report);
    }

}
