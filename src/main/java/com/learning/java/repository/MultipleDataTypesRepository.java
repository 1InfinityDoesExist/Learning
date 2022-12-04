package com.learning.java.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.learning.java.entity.MultipleDataTypes;

@Repository
public interface MultipleDataTypesRepository extends MongoRepository<MultipleDataTypes, String> {

}
