package com.learning.java.controller;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.learning.java.util.PageAPIRecCall;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class TestController {

	@Autowired
	public PageAPIRecCall pageAPIRecCall;

	@PostMapping("/post")
	public void test(@RequestBody Object obj) throws ParseException, IOException {

		log.info("++++++++++++++++++++ " + obj);
		pageAPIRecCall.getPagedDataRefactored(obj);
	}
}
