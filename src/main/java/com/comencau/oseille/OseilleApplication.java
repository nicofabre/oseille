package com.comencau.oseille;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
//@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class OseilleApplication {

	public static void main(String[] args) {
		SpringApplication.run(OseilleApplication.class, args);
	}

}
