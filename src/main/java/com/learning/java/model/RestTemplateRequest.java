package com.learning.java.model;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import java.io.Serializable;
import java.util.Map;

import org.springframework.http.HttpMethod;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestTemplateRequest implements Serializable {

	private static final long serialVersionUID = 6668116445566467631L;

	private boolean paged; // Primitive data type : default value false.

	private String url;

	private HttpMethod method;

	private Map<String, String> headers;

	private Map<String, Object> body;

	private String jsonPath;
}