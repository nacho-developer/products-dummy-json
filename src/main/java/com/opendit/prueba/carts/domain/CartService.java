package com.opendit.prueba.carts.domain;

import java.util.Map;

import com.opendit.prueba.carts.domain.entity.ProductSummary;

import reactor.core.publisher.Mono;

public interface CartService {

	public Mono<Map<String, ProductSummary>> getProductsByUser();

}
