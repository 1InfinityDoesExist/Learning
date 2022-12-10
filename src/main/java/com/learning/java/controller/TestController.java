package com.learning.java.controller;

import java.io.IOException;
import java.util.Map;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.learning.java.util.DeepCopyUtil;
import com.learning.java.util.PageAPIRecCall;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class TestController {

	@Autowired
	public PageAPIRecCall pageAPIRecCall;

	@Autowired
	private DeepCopyUtil deepCopyUtil;

	@PostMapping("/post")
	public void test(@RequestBody Object obj) throws ParseException, IOException {

		log.info("++++++++++++++++++++ " + obj);
		// pageAPIRecCall.getPagedDataRefactored(obj);

		String str = "{\n" + "    \"jsonPath\": \"$.model.entities\",\n" + "    \"body\": null,\n"
				+ "    \"paged\": true,\n" + "    \"method\": \"GET\",\n"
				+ "    \"url\": \"https://ingress-gateway.gaiansolutions.com/tf-web/v1.0/618b6fdef5dacc0001a6b1b0/groups/62efbef6981b350001bf2fee/data?size=1\",\n"
				+ "    \"headers\": {\n" + "        \"content-type\": \"application/json\"\n" + "    }\n" + "}";

		String newStr = deepCopyUtil.deepCopy(str);

		System.out.println(newStr);

	}
}
