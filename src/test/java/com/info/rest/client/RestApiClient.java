package com.info.rest.client;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestApiClient {

	public static void main(String[] args) {

		final int RISK_PREFERENCE = 3;

		// getPortFolioByRiskPreference(RISK_PREFERENCE);
		getrecommendedPortFolio(RISK_PREFERENCE);
	}

	private static void getPortFolioByRiskPreference(int riskPreference) {
		final String uri = "http://localhost:9090/portfolio/" + riskPreference;
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
		System.out.println(result);

	}

	private static void getrecommendedPortFolio(int riskPreference) {

		final String uri = "http://localhost:9090/portfolio/" + riskPreference;
		ClientPortfolio portfolio = new ClientPortfolio();
		portfolio.setBondsAmount(1000);
		portfolio.setForeignAmount(2000);
		portfolio.setLargeCapAmount(3000);
		portfolio.setMidCapAmount(4000);
		portfolio.setSmallCapAmount(5000);

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<ClientPortfolio> entity = new HttpEntity<ClientPortfolio>(portfolio, headers);
		ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.PUT, entity, String.class);

		System.out.println(result);

	}

}
