package com.bridgelabz.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * @author Sachin Barpete
 * @purpose define Http methods
 */
public class RestClient {

	/**
	 * @purpose GET method without Headers
	 * @param url
	 * @return reference of CloseableHttpResponse
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		return httpClient.execute(httpGet);
	}

	/**
	 * @purpose GET method with header
	 * @param url
	 * @param headerMap
	 * @return reference of CloseableHttpResponse
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public CloseableHttpResponse get(String url, HashMap<String, String> headerMap)
			throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		for (Map.Entry<String, String> entry : headerMap.entrySet()) {
			httpGet.addHeader(entry.getKey(), entry.getValue());
		}
		return httpClient.execute(httpGet);
	}

	/**
	 * @param url
	 * @param entityString
	 * @param headerMap
	 * @return reference of CloseableHttpResponse
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public CloseableHttpResponse post(String url, String entityString, HashMap<String, String> headerMap)
			throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url); // http post request
		httpPost.setEntity(new StringEntity(entityString)); // for payload
		for (Map.Entry<String, String> entry : headerMap.entrySet()) {
			httpPost.addHeader(entry.getKey(), entry.getValue());
		}
		return httpClient.execute(httpPost);
	}

}
