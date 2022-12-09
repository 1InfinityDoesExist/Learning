package com.learning.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class JDBCTemplateController {

	@Autowired
	@Qualifier("postgreSqlJdbcTemplate")
	private JdbcTemplate postgreSqlJdbcTemplate;

	@GetMapping("/postgres")
	public ResponseEntity<?> createPostgresTabel() {
		log.info("----Api to create postgres table----");

		String postgresQuery = "create table group_entity (\n"
				+ "    properties_str varchar(255) not null, \n"
				+ "    group_id varchar(255) not null, \n"
				+ "    properties_dto JSON, primary key (properties_str)\n"
				+ ");"; 
				
				
		//"delete from links";
		log.info("-----PostgresQuery : {}", postgresQuery);

		postgreSqlJdbcTemplate.execute(postgresQuery);

		return ResponseEntity.status(HttpStatus.OK).body(new ModelMap().addAttribute("response", "Success"));
	}
}
