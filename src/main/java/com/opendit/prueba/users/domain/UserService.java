package com.opendit.prueba.users.domain;

import com.opendit.prueba.users.domain.entity.User;

import reactor.core.publisher.Flux;

public interface UserService {

	public Flux<User> getAllUsers();
}
