package com.opendit.prueba.products.infraestructure;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.opendit.prueba.config.RestProperties;
import com.opendit.prueba.products.domain.ProductRepository;
import com.opendit.prueba.products.domain.entity.Product;
import com.opendit.prueba.products.domain.entity.Products;
import com.opendit.prueba.shared.ApiClient;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * This class implements the {@link ProductRepository} interface and defines
 * methods to access product data from an external
 * 
 * @author Nacho Dominguez
 */
@AllArgsConstructor
@Repository
@Slf4j
public class RestProductRepository implements ProductRepository {

	private final ApiClient apiClient;
	private final RestProperties restProperties;

	/**
	 * Retrieves all products from the external REST API.
	 * 
	 * @return a {@link Flux} of {@link Product}.
	 */
	@Cacheable(value = "allProducts", key = "0", unless = "#result == null", cacheManager = "cacheManager")
	@Override
	public Flux<Product> findAll() {
		log.info("Method: findAll");
		return apiClient
				.get(restProperties.getDummyJson().getPathProducts(), Products.class)
				.flatMapIterable(Products::getProducts);
	}

}
