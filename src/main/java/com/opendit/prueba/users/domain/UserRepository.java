package com.opendit.prueba.users.domain;

import com.opendit.prueba.users.domain.entity.Users;

import reactor.core.publisher.Mono;

public interface UserRepository {

	public Mono<Users> findAll();

}
