package com.opendit.prueba.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import com.opendit.prueba.shared.ApiClient;

@Configuration
public class ApiClientConfig {

	private final RestProperties restProperties;
	private final String urlBase;

	public ApiClientConfig(RestProperties restProperties, @Value("${rest.dummyJson.urlBase}") String urlBase) {
		this.restProperties = restProperties;
		this.urlBase = urlBase;
	}

	@Bean
	WebClient webClient() {
		return WebClient.builder().defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
	}

	@Bean
	ApiClient apiClient() {
		return new ApiClient(restProperties.getDummyJson() == null ? urlBase : restProperties.getDummyJson().getUrlBase());
	}
	
}
