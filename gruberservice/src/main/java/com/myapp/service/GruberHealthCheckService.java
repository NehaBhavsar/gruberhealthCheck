package com.myapp.service;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.util.Arrays;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.myapp.util.MailUtil;
import com.myapp.vo.GruberReqData;

public class GruberHealthCheckService {
	private static final String GRUBER_URL = "https://api.gruberprep.com/auth";
	private static final String EMAIL_ID = "andy@clairvoyantsoft.com";
	private static final String PASS_WORD = "Welcome123";

	public static void healthCheckService() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Inside health check");

		RestTemplate restTemplate = getRestTemplate();
		HttpEntity<String> httpEntity = getGruberHttpEntity();
		ResponseEntity<GruberReqData> response = restTemplate.exchange(GRUBER_URL, HttpMethod.POST, httpEntity,
				GruberReqData.class);
		HttpStatus code = response.getStatusCode();
		
		if (code != HttpStatus.OK) {
			System.out.println("Host is not reachable.Sending mail");
			MailUtil.sendMail("nehabhavsar.er@gmail.com", GRUBER_URL);
		} else {
			System.out.println("Host is  reachable.");
		}

	}

	public static RestTemplate getRestTemplate() throws Exception {
		KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
		keyStore.load(
				new FileInputStream(new File(
						GruberHealthCheckService.class.getClassLoader().getResource("gruber-keystore.jks").getFile())),
				"changeit".toCharArray());
		SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(
				new SSLContextBuilder().loadTrustMaterial(null, new TrustSelfSignedStrategy())
						.loadKeyMaterial(keyStore, "changeit".toCharArray()).build());
		HttpClient httpClient = HttpClients.custom().setSSLSocketFactory(socketFactory).build();
		ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
		RestTemplate restTemplate = new RestTemplate(requestFactory);
		return restTemplate;
	}

	public static HttpEntity<String> getGruberHttpEntity() {
		GruberReqData reqData = new GruberReqData(EMAIL_ID, PASS_WORD);
		Gson gson = new Gson();
		String jsonData = gson.toJson(reqData);
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> httpEntity = new HttpEntity<String>(jsonData, headers);
		return httpEntity;
	}
}
