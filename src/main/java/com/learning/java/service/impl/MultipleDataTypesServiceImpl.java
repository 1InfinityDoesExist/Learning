package com.learning.java.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.wnameless.json.flattener.FlattenMode;
import com.github.wnameless.json.flattener.JsonFlattener;
import com.learning.java.config.BigDecimalConverter;
import com.learning.java.entity.MultipleDataTypes;
import com.learning.java.repository.MultipleDataTypesRepository;
import com.learning.java.service.MultipleDataTypesService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MultipleDataTypesServiceImpl implements MultipleDataTypesService {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private BigDecimalConverter bigDecimalConverter;

	@Autowired
	private MultipleDataTypesRepository multipleDataTypesRepository;

	@Override
	public MultipleDataTypes persistMultipleDataTypesInDB(MultipleDataTypes multipleDataTypes) {
		return multipleDataTypesRepository.save(multipleDataTypes);
	}

	@Override
	public List<MultipleDataTypes> retrieveMultipleDataTypesFromDB(Map filter) {
		// TODO Auto-generated method stub
		Criteria criteria = new Criteria();
		try {
			Map<String, Object> flattenedFilters = new JsonFlattener(new ObjectMapper().writeValueAsString(filter))
					.withFlattenMode(FlattenMode.KEEP_ARRAYS).flattenAsMap();
			log.info("-----FlattenedFilters : {}", flattenedFilters);
			flattenedFilters.forEach((attributeName, attributeValue) -> {
				log.info("----AttributeName : {} and AttributeValue {}", attributeName, attributeValue);
				if (attributeValue instanceof Collection) {
					criteria.and(attributeName).in((Collection) attributeValue);

				} else {
					criteria.and(attributeName).is(attributeValue);
				}

			});
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		Query query = new Query();
		query.addCriteria(criteria);
		log.info("----Final mongoQuery : {}", query);
		List<MultipleDataTypes> response = mongoTemplate.find(query, MultipleDataTypes.class);
		return response;
	}

}
