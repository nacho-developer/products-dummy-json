package com.opendit.prueba.products.infraestructure;

import org.springframework.stereotype.Repository;

import com.opendit.prueba.config.RestProperties;
import com.opendit.prueba.products.domain.ProductRepository;
import com.opendit.prueba.products.domain.entity.Products;
import com.opendit.prueba.shared.ApiClient;

import reactor.core.publisher.Mono;

@Repository
public class RestProductRepository implements ProductRepository {

	private final ApiClient apiClient;
	private final RestProperties restProperties;

	public RestProductRepository(ApiClient apiClient, RestProperties restProperties) {
		this.apiClient = apiClient;
		this.restProperties = restProperties;
	}

	@Override
	public Mono<Products> findAll() {
		return apiClient.get(restProperties.getDummyJson().getPathProducts(), Products.class);
	}

}
