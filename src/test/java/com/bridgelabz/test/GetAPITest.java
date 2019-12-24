package com.bridgelabz.test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.bridgelabz.base.Base;
import com.bridgelabz.client.RestClient;
import com.bridgelabz.util.Util;

/**
 * @author Sachin Barpete
 * @purpose Http get method API test
 */
public class GetAPITest extends Base {

	Base testBase;
	String url;
	RestClient restClient;
	CloseableHttpResponse closeableHttpResponse;

	@BeforeMethod
	public void setup() {
		testBase = new Base();
		url = properties.getProperty("URL") + properties.getProperty("serviceURL");
	}

	@Test(priority = 1)
	public void getAPITestWithoutHeaders() throws ParseException, IOException {
		restClient = new RestClient();
		closeableHttpResponse = restClient.get(url);
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code ---> " + statusCode);
		Assert.assertEquals(statusCode, RESPONSE_STATUS_OK, "Response Status code is not 200");

		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJSON = new JSONObject(responseString);
		System.out.println("\nResponse JSON from API ---> " + responseJSON);

		String perPageValue = Util.getValueByJSONPath(responseJSON, "/per_page");
		System.out.println("\nValue of per_page = " + perPageValue);
		Assert.assertEquals(Integer.parseInt(perPageValue), 6);

		String totalValue = Util.getValueByJSONPath(responseJSON, "/total");
		System.out.println("Total value = " + totalValue);
		Assert.assertEquals(Integer.parseInt(totalValue), 12);

		String id = Util.getValueByJSONPath(responseJSON, "/data[0]/id");
		String email = Util.getValueByJSONPath(responseJSON, "/data[0]/email");
		String firstName = Util.getValueByJSONPath(responseJSON, "/data[0]/first_name");
		String lastName = Util.getValueByJSONPath(responseJSON, "/data[0]/last_name");

		System.out.println("\nid = " + id);
		System.out.println("email = " + email);
		System.out.println("first name = " + firstName);
		System.out.println("lastname = " + lastName);

		Header[] headerArray = closeableHttpResponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();
		for (Header header : headerArray) {
			allHeaders.put(header.getName(), header.getValue());
		}

		System.out.println("\nHeaders Array ---> " + allHeaders);
	}

	@Test(priority = 2)
	public void getAPITestWithHeaders() throws ParseException, IOException {
		restClient = new RestClient();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		closeableHttpResponse = restClient.get(url, headerMap);
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code ---> " + statusCode);
		Assert.assertEquals(statusCode, RESPONSE_STATUS_OK, "Response Status code is not 200");

		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJSON = new JSONObject(responseString);
		System.out.println("\nResponse JSON from API ---> " + responseJSON);

		String perPageValue = Util.getValueByJSONPath(responseJSON, "/per_page");
		System.out.println("\nValue of per_page = " + perPageValue);
		Assert.assertEquals(Integer.parseInt(perPageValue), 6);

		String totalValue = Util.getValueByJSONPath(responseJSON, "/total");
		System.out.println("Total value = " + totalValue);
		Assert.assertEquals(Integer.parseInt(totalValue), 12);

		String id = Util.getValueByJSONPath(responseJSON, "/data[0]/id");
		String email = Util.getValueByJSONPath(responseJSON, "/data[0]/email");
		String firstName = Util.getValueByJSONPath(responseJSON, "/data[0]/first_name");
		String lastName = Util.getValueByJSONPath(responseJSON, "/data[0]/last_name");

		System.out.println("\nid = " + id);
		System.out.println("email = " + email);
		System.out.println("first name = " + firstName);
		System.out.println("lastname = " + lastName);

		Header[] headerArray = closeableHttpResponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();
		for (Header header : headerArray) {
			allHeaders.put(header.getName(), header.getValue());
		}

		System.out.println("\nHeaders Array ---> " + allHeaders);
	}
}
