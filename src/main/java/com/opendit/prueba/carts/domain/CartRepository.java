package com.opendit.prueba.carts.domain;

import com.opendit.prueba.carts.domain.entity.Carts;

import reactor.core.publisher.Mono;

public interface CartRepository {
	
	public Mono<Carts> findByUserId(Long userId);

}
