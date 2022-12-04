package com.learning.java.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.learning.java.entity.MultipleDataTypes;
import com.learning.java.service.MultipleDataTypesService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MultipleDataTypesController {

	@Autowired
	private MultipleDataTypesService multipleDataTypesService;

	@RequestMapping(path = "/multiple-data-types", consumes = "application/json", produces = "application/json", method = RequestMethod.POST)
	public ResponseEntity<?> persistMultipleDataTypesInDB(@RequestBody MultipleDataTypes multipleDataTypes) {

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(multipleDataTypesService.persistMultipleDataTypesInDB(multipleDataTypes));
	}

	@RequestMapping(path = "/multiple-data-types/filter", consumes = "application/json", produces = "application/json", method = RequestMethod.POST)
	public ResponseEntity<?> retrieveMultipleDataTypesFromDB(@RequestBody Map filter) {

		return ResponseEntity.status(HttpStatus.OK)
				.body(multipleDataTypesService.retrieveMultipleDataTypesFromDB(filter));
	}

}
