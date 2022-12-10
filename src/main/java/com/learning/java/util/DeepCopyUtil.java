package com.learning.java.util;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class DeepCopyUtil {

	private static final ObjectMapper objectMapper;
	static {
		objectMapper = new ObjectMapper();
	}

	public <T> T deepCopy(T response) {

		try {
			byte[] bytes = objectMapper.writeValueAsBytes(response);
			return (T) objectMapper.readValue(bytes, response.getClass());
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Cloning Issue: " + e.getMessage());
		}
	}
}
