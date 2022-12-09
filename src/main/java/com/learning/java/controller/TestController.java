package com.learning.java.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@RestController
public class TestController {

	@GetMapping("/test")
	public ResponseEntity<?> duplicatePrimaryKeyTest() {

		for (int iter = 0; iter < 5; iter++) {
			MyThread1 temp = new MyThread1(iter);
			temp.start();
		}

		return ResponseEntity.status(HttpStatus.OK).body(new ModelMap().addAttribute("response", "success"));
	}
}

class MyThread1 extends Thread {
	int k;

	public MyThread1(int i) {
		k = i;
	}

	@Override
	public void run() {
		Unirest.setTimeouts(0, 0);
		try {
			HttpResponse<String> response = Unirest.post(
					"http://ingress-gateway.gaiansolutions.com/tf-web/v1.0/618b6fdef5dacc0001a6b1b0/schemas/638d9571736ad10001c35cc8/instance?upsert=false")
					.header("Content-Type", "application/json")
					.body("{\n    \"stringTest\": \"Bye-804\",\n    \"numberTest\": 1561,\n    \"objectArrayTest\": [\n        12,\n        20,\n        30,\n        40,\n        50\n    ]\n}")
					.asString();
			
			
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Thread no. " + k);

	}
}
