package com.learning.java.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * HttpURLConnection <-> HttpsURLConnection
 * 
 * @author AP
 *
 */
@Slf4j
@Component
public class FinalURLUtil {

	public static String getFinalURL(String url) throws MalformedURLException, IOException {
		log.info("----Get final URL of : {}", url);
		HttpURLConnection connection;
		String finalUrl = url;
		do {
			connection = (HttpURLConnection) new URL(finalUrl).openConnection();
			connection.setInstanceFollowRedirects(false);
			connection.setUseCaches(false);
			connection.setRequestMethod("GET");
			connection.connect();

			int responseCode = connection.getResponseCode();
			if (responseCode >= 300 && responseCode < 400) {
				String redirectUrl = connection.getHeaderField("Location");
				if (ObjectUtils.isEmpty(redirectUrl)) {
					break;
				}
				finalUrl = redirectUrl;
			} else {
				break;
			}
		} while (connection.getResponseCode() != HttpURLConnection.HTTP_OK);
		connection.disconnect();
		return finalUrl.replaceAll(" ", "%20");

	}
}
