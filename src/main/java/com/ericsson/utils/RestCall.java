package com.ericsson.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import javax.net.ssl.SSLContext;

import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

public class RestCall {

	private static final Logger logger = LoggerFactory.getLogger(RestCall.class);
	
	private Map<String, String> header = new HashMap<String, String>();
	
	public void addHeaderParams(String key, String value){
		this.header.put(key, value);
	}
	
	private Map<String, String> queryParams = new HashMap<String, String>();
	
	public void addQueryParams(String key, String value){
		this.queryParams.put(key, value);
	}
	
	private static final String CONFIG_LOCATION = System.getProperty("transactions.config");

	public JSONObject sendGetRestCall(String url, Map<String, String> header, Map<String, String> queryParams)
			throws Exception {
		
		logger.debug("Start REST Call");
		URIBuilder builder = new URIBuilder(url);
		if (null != queryParams && queryParams.size() > 0){
			Iterator<String> queryParamsSet = queryParams.keySet().iterator();
			while (queryParamsSet.hasNext()) {
				String key = queryParamsSet.next();
				builder.setParameter(key, queryParams.get(key));
			}
		}
		logger.debug("Start REST Call Get Query Params Set ");
		URI uri = builder.build();
		HttpGet httpget = new HttpGet(uri);
		logger.debug("Start REST Call Get Request Ready ");
		if (null != header && header.size() > 0){
			Iterator<String> headerSet = header.keySet().iterator();
			while (headerSet.hasNext()) {
				String key = headerSet.next();
				httpget.addHeader(key, header.get(key));
			}	
		}
		
		logger.info("\nSending 'GET' request to URL : " + uri.toString());
		
		HttpClient client = HttpClientBuilder.create().build();
		HttpResponse response = client.execute(httpget);

		logger.info("Response Code : " + response.getStatusLine().getStatusCode());

		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		
		JSONObject obj = new JSONObject();
		obj.put("status", response.getStatusLine().getStatusCode());
        obj.put("result", result.toString());
		logger.info("Response :: " + result.toString());
		return obj;
	}
	
	
	public JSONObject makeGetRestCall(String url, Map<String, String> header, Map<String, String> queryParams)
			throws Exception {

		Iterator<String> queryParamsSet = queryParams.keySet().iterator();
		URIBuilder builder = new URIBuilder(url);

		while (queryParamsSet.hasNext()) {
			String key = queryParamsSet.next();
			builder.setParameter(key, queryParams.get(key));
		}
		URI uri = builder.build();
		HttpGet httpget = new HttpGet(uri);

		if (null != header && header.size() > 0){
			Iterator<String> headerSet = header.keySet().iterator();
			while (headerSet.hasNext()) {
				String key = headerSet.next();
				httpget.addHeader(key, header.get(key));
			}	
		}
		
		logger.debug("\nSending 'GET' request to URL : " + uri.toString());
		
		logger.info("\nSending 'GET' request to URL : " + uri.toString());
		
		HttpClient client = HttpClientBuilder.create().build();
		HttpResponse response = client.execute(httpget);

		logger.debug("Response Code : " + response.getStatusLine().getStatusCode());
		logger.info("Response Code : " + response.getStatusLine().getStatusCode());
		

		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		
		JSONObject obj = new JSONObject();
		obj.put("status", response.getStatusLine().getStatusCode());
        obj.put("result", result.toString());
        logger.info("Response :: " + result.toString());
        if (null != result && result.toString().length() > 0){
        	obj = new JSONObject(result.toString());
        }
		return obj;
	}
	
	public JSONObject sendDeleteRestCall(String url, Map<String, String> header, Map<String, String> queryParams)
			throws Exception {

		logger.info("Delete Request for profile : " + queryParams + "With URL : " + url);
		Iterator<String> queryParamsSet = queryParams.keySet().iterator();
		URIBuilder builder = new URIBuilder(url);
		logger.info("Preparing Query Params");
		while (queryParamsSet.hasNext()) {
			String key = queryParamsSet.next();
			builder.setParameter(key, queryParams.get(key));
		}
		URI uri = builder.build();
		logger.info("Preparing URI");
		HttpDelete httpDelete = new HttpDelete(uri);
		logger.info("Prepating Header");
		Iterator<String> headerSet = header.keySet().iterator();
		while (headerSet.hasNext()) {
			String key = headerSet.next();
			httpDelete.addHeader(key, header.get(key));
		}
		logger.info("Header Prepared");
		HttpClient client = HttpClientBuilder.create().build();
		
		HttpResponse response = client.execute(httpDelete);

		int responseStatusCode = response.getStatusLine().getStatusCode();
		logger.info("\nSending 'DELETE' request to URL : " + uri.toString());
		logger.info("Response Code : " + responseStatusCode);

		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		JSONObject obj = new JSONObject();
		
		obj.put("status", response.getStatusLine().getStatusCode());
        obj.put("result", result.toString());
		logger.info("Response :: " + result.toString());
		return obj;
	}

	public JSONObject sendPostRestCall(String url, Map<String, String> header, Map<String, String> queryParams,
			String body) throws Exception {

		logger.debug("Making REST POSt Call");
		URIBuilder builder = new URIBuilder(url);

		if (null != queryParams && !queryParams.isEmpty()) {
			Iterator<String> queryParamsSet = queryParams.keySet().iterator();
			while (queryParamsSet.hasNext()) {
				String key = queryParamsSet.next();
				builder.setParameter(key, queryParams.get(key));

			}
		}

		URI uri = builder.build();
		HttpPost httpPost = new HttpPost(uri);

		if (null != header && header.keySet().size() > 0) {
			Iterator<String> headerParamsSet = header.keySet().iterator();

			while (headerParamsSet.hasNext()) {
				String key = headerParamsSet.next();
				httpPost.addHeader(key, header.get(key));
			}
		}

		if (null != body && body.trim().length() > 0) {
			StringEntity input = new StringEntity(body);
			input.setContentType("application/json");
			httpPost.setEntity(input);
		}
		logger.debug(uri.toString());
		HttpClient client = HttpClientBuilder.create().build();
		HttpResponse response = client.execute(httpPost);

		logger.info("\nSending 'POST' request to URL : " + uri.toString());
		logger.info("Response Code : " + response.getStatusLine().getStatusCode());
		logger.debug("Response Code : " + response.getStatusLine().getStatusCode());

		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		logger.info("Result : " + result.toString());

		JSONObject resultObj = new JSONObject();
		if (StringUtils.isNotBlank(result.toString())) {
			resultObj = new JSONObject();
			resultObj.put("result", result.toString());
			resultObj.put("status", response.getStatusLine().getStatusCode());
			logger.info("Getting result headers : ");
			Header[] responseHeaders = response.getAllHeaders();
			JSONObject headerObj = new JSONObject();
			if (null != responseHeaders && responseHeaders.length > 0) {
				for (Header headerTemp : responseHeaders) {
					headerObj.put(headerTemp.getName(), headerTemp.getValue());
				}
			}
			logger.info("Result Headers  : " + headerObj);
			resultObj.put("resultHeader", headerObj);
		}
		return resultObj;
	}

	public String sendPostRestCallUrlEncoding(String url, Map<String, String> header, Map<String, String> queryParams,
			String body) throws Exception {

		Iterator<String> queryParamsSet = queryParams.keySet().iterator();
		Iterator<String> headerParamsSet = header.keySet().iterator();
		URIBuilder builder = new URIBuilder(url);

		while (queryParamsSet.hasNext()) {
			String key = queryParamsSet.next();
			builder.setParameter(key, queryParams.get(key));

		}
		URI uri = builder.build();
		HttpPost httpPost = new HttpPost(uri);
		while (headerParamsSet.hasNext()) {
			String key = headerParamsSet.next();
			httpPost.addHeader(key, header.get(key));
		}

		// if (null != body && body.trim().length() > 0){
		StringEntity input = new StringEntity(new String());
		input.setContentType("application/x-www-form-urlencoded");
		httpPost.setEntity(input);
		// }

		logger.debug(httpPost.getURI().toString());
		
		HttpClient client = HttpClientBuilder.create().build();
		HttpResponse response = client.execute(httpPost);

		
		logger.info("\nSending 'POST' request to URL : " + uri.toString());
		logger.info("Response Code : " + response.getStatusLine().getStatusCode());

		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}

		return result.toString();
	}
	
	public String sendHttpsPostRestCallUrlEncoding(String url, Map<String, String> header, Map<String, String> queryParams,
			String body) throws Exception {
		
		Iterator<String> queryParamsSet = queryParams.keySet().iterator();
		Iterator<String> headerParamsSet = header.keySet().iterator();
		URIBuilder builder = new URIBuilder(url);

		while (queryParamsSet.hasNext()) {
			String key = queryParamsSet.next();
			builder.setParameter(key, queryParams.get(key));

		}
		URI uri = builder.build();
		HttpPost httpPost = new HttpPost(uri);
		while (headerParamsSet.hasNext()) {
			String key = headerParamsSet.next();
			httpPost.addHeader(key, header.get(key));
		}

		// if (null != body && body.trim().length() > 0){
		StringEntity input = new StringEntity(new String());
		input.setContentType("application/x-www-form-urlencoded");
		httpPost.setEntity(input);
		// }
		
		RequestConfig customRequestConfig = RequestConfig.custom().build();
		
		HttpClientBuilder httpBuilder = HttpClientBuilder.create();
        SSLContext context = SSLContext.getDefault();
        httpBuilder.setDefaultRequestConfig(customRequestConfig);

        SSLConnectionSocketFactory sslConnectionFactory = new SSLConnectionSocketFactory(context, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        httpBuilder.setSSLSocketFactory(sslConnectionFactory);
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(context);
        PlainConnectionSocketFactory plainsf = new PlainConnectionSocketFactory();

        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory> create().register("http", plainsf).register("https", sslsf).build();

        HttpClientConnectionManager ccm = new BasicHttpClientConnectionManager(registry);
        HttpClient httpClient = HttpClients.custom().setConnectionManager(ccm).build();
        
        
        //logger.debug("\nSending 'POST' request to URL : " + httpPost.getURI().getRawPath());
        HttpResponse response = httpClient.execute(httpPost);

		
		logger.info("\nSending 'POST' request to URL : " + uri.toString());
		logger.info("Response Code : " + response.getStatusLine().getStatusCode());

		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}

		return result.toString();
	}
	
	public static void main(String[] args) {
		RestCall call = new RestCall();
		call.compareScopes();
		/*try {
			//System.out.println(call.sendGetRestCall("http://10.255.254.178:8103/profile_management/v1/user/profile", new HashMap(), new HashMap()));
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Authorization", "Basic QS1jSVJwbHBxU09yXzp2VVBZNEZka252");
			Map<String, String> params = new HashMap<String, String>();
			params.put("scope", "TMO_ID_profile billing_information associated_lines iam_account_lock_information");
			params.put("grant_type", "client_credentials");
			//System.out.println(call.sendHttpsPostRestCallUrlEncoding("https://66.94.18.136",header,params, null));
			
			URIBuilder uri = new URIBuilder("https://66.94.18.136");
            for (String name : params.keySet()) {
                uri.addParameter(name, params.get(name));
            }
            HttpPost request = new HttpPost(uri.build());
            request.setHeader("Accept", MediaType.APPLICATION_JSON);
            request.addHeader("Content-type", MediaType.APPLICATION_JSON);
            for (String name : headers.keySet()) {
                request.setHeader(name, headers.get(name));
            }
            
            
            IAMHttpsConnectionPool.getInstance().getConnection().execute(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	public void compareScopes(){
		
		try {
			Scanner prodscanner = new Scanner(
					new File(CONFIG_LOCATION + File.separator + "production.txt"));
			
			StringBuilder build = new StringBuilder();
			while (prodscanner.hasNext()){
				build.append(prodscanner.next());
			}
			
			build.toString().replace("\\", "");
			Scanner labscanner = new Scanner(
					new File(CONFIG_LOCATION + File.separator + "lab.txt"));
			
			StringBuilder build1 = new StringBuilder();
			while (labscanner.hasNext()){
				build1.append(labscanner.next());
			}
			build1.toString().replace("\\", "");
			Gson gson = new Gson();
			JSONObject prodObj = gson.fromJson(build.toString(), JSONObject.class);
			JSONObject labObj = gson.fromJson(build1.toString(), JSONObject.class);
			
			logger.debug("prodObj" + prodObj);
			logger.debug("labObj" + labObj);
			
			prodscanner.close();
			labscanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	public JSONObject sendPutRestCall(String url, Map<String, String> header, Map<String, String> queryParams, String body)
			throws Exception {

		URIBuilder builder = new URIBuilder(url);
		if (null != queryParams && queryParams.size() > 0){
			Iterator<String> queryParamsSet = queryParams.keySet().iterator();
			while (queryParamsSet.hasNext()) {
				String key = queryParamsSet.next();
				builder.setParameter(key, queryParams.get(key));

			}
		}
		
		URI uri = builder.build();
		HttpPut httpPost = new HttpPut(uri);
		
		if (null != header && header.keySet().size() > 0){
			Iterator<String> headerParamsSet = header.keySet().iterator();
			
			while (headerParamsSet.hasNext()) {
				String key = headerParamsSet.next();
				httpPost.addHeader(key, header.get(key));
			}	
		}
		

		if (null != body && body.trim().length() > 0) {
			StringEntity input = new StringEntity(body);
			input.setContentType("application/json");
			httpPost.setEntity(input);
		}

		HttpClient client = HttpClientBuilder.create().build();
		HttpResponse response = client.execute(httpPost);

		logger.info("\nSending 'PUT' request to URL : " + uri.toString());
		logger.info("Response Code : " + response.getStatusLine().getStatusCode());

		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		logger.info("Result : " + result.toString());
		
		JSONObject resultObj = new JSONObject();
		if (StringUtils.isNotBlank(result.toString())){
			resultObj = new JSONObject();
			resultObj.put("result", result.toString());
			resultObj.put("status", response.getStatusLine().getStatusCode());
			logger.info("Getting result headers : ");
			Header[] responseHeaders = response.getAllHeaders();
			JSONObject headerObj = new JSONObject();
			if (null != responseHeaders && responseHeaders.length > 0){
				for (Header headerTemp : responseHeaders){
					headerObj.put(headerTemp.getName(), headerTemp.getValue());
				}
			}
			logger.info("Result Headers  : " + headerObj);
			resultObj.put("resultHeader",headerObj );
		}
		return resultObj;
	}
}
