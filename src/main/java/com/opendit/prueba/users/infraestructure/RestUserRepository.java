package com.opendit.prueba.users.infraestructure;

import org.springframework.stereotype.Repository;

import com.opendit.prueba.config.RestProperties;
import com.opendit.prueba.shared.ApiClient;
import com.opendit.prueba.users.domain.UserRepository;
import com.opendit.prueba.users.domain.entity.Users;

import reactor.core.publisher.Mono;

@Repository
public class RestUserRepository implements UserRepository {

	private final ApiClient apiClient;
	private final RestProperties restProperties;

	public RestUserRepository(ApiClient apiClient, RestProperties restProperties) {
		this.apiClient = apiClient;
		this.restProperties = restProperties;
	}

	@Override
	public Mono<Users> findAll() {
		return apiClient.get(restProperties.getDummyJson().getPathUsers(), Users.class);
	}

}
