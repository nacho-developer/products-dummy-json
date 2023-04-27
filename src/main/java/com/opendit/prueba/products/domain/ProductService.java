package com.opendit.prueba.products.domain;

import com.opendit.prueba.products.domain.entity.BrandCount;
import com.opendit.prueba.products.domain.entity.CategoryCount;
import com.opendit.prueba.products.domain.entity.Product;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {
	
	Flux<Product> getAllProducts();

	Mono<Product> getHighestPricedProduct();

	Mono<Product> getLowestPricedProduct();

	Mono<Double> getAveragePrice();

	Flux<BrandCount> getProductsByBrand();

	Flux<CategoryCount> getProductsByCategory();

}
