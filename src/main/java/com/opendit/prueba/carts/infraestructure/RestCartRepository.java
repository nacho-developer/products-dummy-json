package com.opendit.prueba.carts.infraestructure;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.opendit.prueba.carts.domain.CartRepository;
import com.opendit.prueba.carts.domain.entity.Carts;
import com.opendit.prueba.config.RestProperties;
import com.opendit.prueba.shared.ApiClient;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * Implementation of {@link CartRepository} that retrieves data from a REST API.
 * 
 * @author Nacho Dominguez
 */
@AllArgsConstructor
@Repository
@Slf4j
public class RestCartRepository implements CartRepository {

	private final ApiClient apiClient;
	private final RestProperties restProperties;

	/**
	 * Retrieves the cart of a specific user by their ID from the REST API.
	 * 
	 * @param userId the ID of the user whose cart is to be retrieved.
	 * @return a {@link Mono} of a {@link Carts} object representing the user's cart.
	 */
	@Cacheable(value = "findByUserId", key = "#userId", unless = "#result == null", cacheManager = "cacheManager")
	@Override
	public Mono<Carts> findByUserId(Long userId) {
		log.info("Method: findByUserId with userId=" + userId);
		var url = restProperties.getDummyJson().getPathCarts().replace("{userId}", userId.toString());
		return apiClient.get(url, Carts.class);
	}

}
