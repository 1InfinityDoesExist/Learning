package com.learning.java.config;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import lombok.extern.slf4j.Slf4j;

/**
 * /** org.hsqldb.jdbcDriver
 * 
 * @author gaian
 *
 */

@Slf4j
@Configuration
public class JDBCTemplateConfig {

	@Bean("postgreSqlJdbcTemplate")
	public JdbcTemplate jdbcTemplate() {
		log.info("-----Creating bean of jdbcTemplate-----");
		DataSource dataSource = DataSourceBuilder.create().driverClassName("org.postgresql.Driver")
				.url("jdbc:postgresql://localhost:5432/test_db?stringtype=unspecified").username("postgres")
				.password("root").build();
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		log.info("-----JDBC Bean  : {}", jdbcTemplate.getClass());

		return jdbcTemplate;
	}
}
