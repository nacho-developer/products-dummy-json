package com.opendit.prueba.products.domain;

import com.opendit.prueba.products.domain.entity.Product;

import reactor.core.publisher.Flux;

public interface ProductRepository {

	public Flux<Product> findAll();

}
