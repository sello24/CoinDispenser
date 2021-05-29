package com.sello.coindispenser.configurations;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/*
 * This file is mainly used for DB connections configurations, the values are read from application.properties
 */


@Configuration
@EnableAutoConfiguration
public class mySqlConfiguration {
	
	@Value("${coindispenserdb.url}")
	private String Url;
	@Value("${coindispenserdb.username}")
	private String username;
	@Value("${coindispenserdb.password}")
	private String password;

	@Bean
	public DataSource mysqlDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl(Url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);

		return dataSource;
	}
	
}
