package com.opendit.prueba.products.domain;

import com.opendit.prueba.products.domain.entity.Products;

import reactor.core.publisher.Mono;

public interface ProductRepository {

	public Mono<Products> findAll();

}
