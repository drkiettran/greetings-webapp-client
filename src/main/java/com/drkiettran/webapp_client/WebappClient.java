package com.drkiettran.webapp_client;

import java.nio.charset.Charset;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * localhost url: http://localhost:8080/api/v1/greetings/sayHello
 * 
 * example to run: <code>
 * 		java -cp webapp_client-jar-with-dependencies.jar com.drkiettran.webapp_client.WebappClient
 * 					http://localhost:8080/api/v1/greetings/sayHello my-name "Greetings from VA"
 * 
 * Go here https://www.base64encode.org/ to encode username:password.
 * 
 * user1:secret1
 * 
 * 		curl -X PUT http://localhost:8080/api/v1/greetings/sayHello 
 * 				--data "{\"name\":\"my-name\",\"message\":\"Greetings from Herndon\"}" 
 * 				-H "Content-type: application/json" -H "Authorization: Basic dXNlcjE6c2VjcmV0MQ=="
 * </code>
 * 
 * @author ktran
 *
 */
public class WebappClient {
	public static void main(String... args) {
		if (args.length < 2) {
			System.out.println(
					"Run as java -cp webapp_client-jar-with-dependencies.jar com.drkiettran.webapp_client.WebappClient url name message");
			return;
		}
		String url = args[0];
		Message message = new Message();
		message.setName(args[1]);
		message.setMessage(args[2]);

		System.out.println("url: " + url);
		System.out.println("message: " + message);
		WebappClient webappClient = new WebappClient();
		Message responseMessage = webappClient.sayHello(url, message);
		System.out.println("response: " + responseMessage);
	}

	/**
	 * Invoke remote REST API with a message, Wait for a return message
	 * 
	 * @param resourceUrl
	 * @param message
	 */
	private Message sayHello(String resourceUrl, Message message) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();

		String username = "user1";
		String password = "secret1";
		String auth = username + ":" + password;
		byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
		String authHeader = "Basic " + new String(encodedAuth);
		headers.set("Authorization", authHeader);

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Message> putRequest = new HttpEntity<>(message, headers);
		ResponseEntity<Message> response = restTemplate.exchange(resourceUrl, HttpMethod.PUT, putRequest,
				Message.class);
		return response.getBody();
	}
}
