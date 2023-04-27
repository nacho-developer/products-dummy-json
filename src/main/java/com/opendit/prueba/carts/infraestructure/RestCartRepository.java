package com.opendit.prueba.carts.infraestructure;

import org.springframework.stereotype.Repository;

import com.opendit.prueba.carts.domain.CartRepository;
import com.opendit.prueba.carts.domain.entity.Carts;
import com.opendit.prueba.config.RestProperties;
import com.opendit.prueba.shared.ApiClient;

import reactor.core.publisher.Mono;

@Repository
public class RestCartRepository implements CartRepository {

	private final ApiClient apiClient;
	private final RestProperties restProperties;

	public RestCartRepository(ApiClient apiClient, RestProperties restProperties) {
		this.apiClient = apiClient;
		this.restProperties = restProperties;
	}

	@Override
	public Mono<Carts> findByUserId(Long userId) {
		var url = restProperties.getDummyJson().getPathCarts().replace("{userId}", userId.toString());
		return apiClient.get(url, Carts.class);
	}

}
