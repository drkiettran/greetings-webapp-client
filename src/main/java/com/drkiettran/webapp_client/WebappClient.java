package com.drkiettran.webapp_client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * localhost url: http://localhost:8080/api/v1/greetings/sayHello
 * 
 * @author ktran
 *
 */
public class WebappClient {
	public static void main(String... args) {
		if (args.length < 2) {
			System.out.println(
					"Run as java -cp webapp_client-jar-with-dependencies.jar com.drkiettran.webapp_client.WebappClient url message");
			return;
		}
		String url = args[0];
		String message = args[1];

		WebappClient webappClient = new WebappClient();
		String responseMessage = webappClient.sayHello(url, message);
		System.out.println("response: " + responseMessage);
	}

	/**
	 * Invoke remote REST API with a message, Wait for a return message
	 * 
	 * @param resourceUrl
	 * @param message
	 */
	private String sayHello(String resourceUrl, String message) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestUpdate = new HttpEntity<>(message, headers);
		ResponseEntity<String> response = restTemplate.exchange(resourceUrl, HttpMethod.PUT, requestUpdate,
				String.class);
		return response.getBody();
	}
}
