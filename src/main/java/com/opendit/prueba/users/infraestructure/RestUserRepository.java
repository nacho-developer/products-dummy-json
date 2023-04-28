package com.opendit.prueba.users.infraestructure;

import org.springframework.stereotype.Repository;

import com.opendit.prueba.config.RestProperties;
import com.opendit.prueba.shared.ApiClient;
import com.opendit.prueba.users.domain.UserRepository;
import com.opendit.prueba.users.domain.entity.Users;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

/**
 * Implementation of {@link UserRepository} that retrieves data from a REST API.
 * 
 * @author Nacho Dominguez
 */
@AllArgsConstructor
@Repository
public class RestUserRepository implements UserRepository {

	private final ApiClient apiClient;
	private final RestProperties restProperties;

	/**
	 * Retrieves all users from an external API via the {@link ApiClient}.
	 * 
	 * @return a {@link Mono} of a {@link Users} object representing all the users
	 *         retrieved.
	 */
	@Override
	public Mono<Users> findAll() {
		return apiClient.get(restProperties.getDummyJson().getPathUsers(), Users.class);
	}

}
