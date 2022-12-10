package com.learning.java.util;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.thymeleaf.util.MapUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jackson.NodeType;
import com.github.wnameless.json.flattener.FlattenMode;
import com.github.wnameless.json.flattener.JsonFlattener;
import com.jayway.jsonpath.JsonPath;
import com.learning.java.model.RestTemplateRequest;

import lombok.extern.slf4j.Slf4j;

/**
 * { "jsonPath": "model.entities", "body": null, "paged": true, "method": "GET",
 * "url": "xxx?size=1", "headers": { "content-type": "application/json" } }
 * 
 * 
 * @author
 *
 */

@Slf4j
@Component
public class PageAPIRecCall {

	@Autowired
	private GenericRestTemplateUtil genericRestTemplateUtil;

	private static final ObjectMapper objectMapper;
	static {
		objectMapper = new ObjectMapper();
	}

	public void getPagedData(Object restApiRequest) throws JsonProcessingException, ParseException {

		log.info("----RestApiRequest : {}", restApiRequest);
		RestTemplateRequest restTemplateRequest = objectMapper.convertValue(restApiRequest, RestTemplateRequest.class);
		restTemplateRequest.setMethod(org.springframework.http.HttpMethod.GET);

		HttpHeaders headers = new HttpHeaders();
		if (!MapUtils.isEmpty(restTemplateRequest.getHeaders())) {
			restTemplateRequest.getHeaders().forEach(headers::add);
		}

		if (restTemplateRequest.getMethod().compareTo(HttpMethod.GET) == 0 && restTemplateRequest.isPaged()) {
			int page = 0;
			boolean flag = true;
			do {
				String pagedUrl = restTemplateRequest.getUrl() + "?page=" + page++ + "&size=10";
				Object response = genericRestTemplateUtil.performRest(restTemplateRequest.getMethod(), pagedUrl,
						headers, restTemplateRequest.getBody(), Object.class);

				Map<String, Object> flatternMap = new JsonFlattener(
						((JSONObject) new JSONParser().parse(objectMapper.writeValueAsString(response))).toJSONString())
						.withFlattenMode(FlattenMode.KEEP_ARRAYS).flattenAsMap();

				List object = (List) flatternMap.get(restTemplateRequest.getJsonPath());
				log.info("-------ObjectSize : {}", object.size());
				if (CollectionUtils.isEmpty(object)) {
					log.info("------Flag : {}", flag);
					flag = false;
				}
			} while (flag);
			log.info("----Pagination Success");
		}
	}

	public void getPagedDataRefactored(Object restApiRequest) throws ParseException, IOException {

		log.info("----RestApiRequest : {}", restApiRequest);
		RestTemplateRequest restTemplateRequest = objectMapper.convertValue(restApiRequest, RestTemplateRequest.class);
		restTemplateRequest.setMethod(org.springframework.http.HttpMethod.GET);

		HttpHeaders headers = new HttpHeaders();
		if (!MapUtils.isEmpty(restTemplateRequest.getHeaders())) {
			restTemplateRequest.getHeaders().forEach(headers::add);
		}

		if (restTemplateRequest.getMethod().compareTo(HttpMethod.GET) == 0 && restTemplateRequest.isPaged()) {
			int page = 0;
			boolean flag = true;
			do {
				String pagedUrl = restTemplateRequest.getUrl() + "?page=" + page++ + "&size=1";
				Object response = genericRestTemplateUtil.performRest(restTemplateRequest.getMethod(), pagedUrl,
						headers, restTemplateRequest.getBody(), Object.class);

				JsonNode jsonNode = objectMapper.readTree(objectMapper.writeValueAsString(response));
				log.info("-----JsonPath : {}", restTemplateRequest.getJsonPath());
				Object read = JsonPath.read(objectMapper.writeValueAsString(jsonNode),
						restTemplateRequest.getJsonPath());
				NodeType jsonNodeType = NodeType
						.getNodeType(objectMapper.readValue(objectMapper.writeValueAsBytes(read), JsonNode.class));
				log.info("----NodeType : {}", jsonNodeType);

				if (jsonNodeType.equals(NodeType.ARRAY)) {
					if (CollectionUtils.isEmpty(List.class.cast(read))) {
						log.info("------Flag : {}", flag);
						flag = false;
					}
				}
			} while (flag);
			log.info("----Pagination Success");
		}
	}

}
