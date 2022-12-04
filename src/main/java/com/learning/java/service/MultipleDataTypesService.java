package com.learning.java.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.learning.java.entity.MultipleDataTypes;

@Service
public interface MultipleDataTypesService {

	MultipleDataTypes persistMultipleDataTypesInDB(MultipleDataTypes multipleDataTypes);

	List<MultipleDataTypes> retrieveMultipleDataTypesFromDB(Map filter);

}
