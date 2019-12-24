package com.bridgelabz.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.bridgelabz.base.Base;
import com.bridgelabz.client.RestClient;
import com.bridgelabz.model.User;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Sachin Barpete
 * @purpose Http post method API test
 */
public class PostAPITest extends Base {

	Base testBase;
	String url;
	RestClient restClient;
	CloseableHttpResponse closeableHttpResponse;

	@BeforeMethod
	public void setup() {
		testBase = new Base();
		url = properties.getProperty("URL") + properties.getProperty("serviceURL");
	}

	@Test
	public void postAPITest() throws JsonGenerationException, JsonMappingException, IOException {
		restClient = new RestClient();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		ObjectMapper mapper = new ObjectMapper();
		User user = new User("Sachin", "Automation Enginner");
		mapper.writeValue(new File(System.getProperty("user.dir") + "/src/main/java/com/bridgelabz/model/users.json"),
				user);

		String userJsonString = mapper.writeValueAsString(user);
		System.out.println(userJsonString);

		closeableHttpResponse = restClient.post(url, userJsonString, headerMap);
		System.out.println(closeableHttpResponse.getStatusLine());
		Assert.assertEquals(closeableHttpResponse.getStatusLine().getStatusCode(), RESPONSE_STATUS_CREATED,
				"Response status code is not 201 ");
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJSON = new JSONObject(responseString);
		System.out.println("Response from API : " + responseJSON);

		User userResponseObject = mapper.readValue(responseString, User.class);
		System.out.println(userResponseObject);
		Assert.assertTrue(user.getName().equals(userResponseObject.getName()));
		Assert.assertTrue(user.getJob().equals(userResponseObject.getJob()));
		System.out.println("id = " + userResponseObject.getId());
		System.out.println("createdAt = " + userResponseObject.getCreatedAt());
	}
}
