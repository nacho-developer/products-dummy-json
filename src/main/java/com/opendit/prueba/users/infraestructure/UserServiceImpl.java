package com.opendit.prueba.users.infraestructure;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.opendit.prueba.users.domain.UserRepository;
import com.opendit.prueba.users.domain.UserService;
import com.opendit.prueba.users.domain.entity.User;
import com.opendit.prueba.users.domain.entity.Users;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Cacheable(value = "allUsers", key = "0", unless = "#result == null", cacheManager = "cacheManager")
	@Override
	public Flux<User> getAllUsers() {
		log.info("Method: getAllUsers");
		return userRepository.findAll().flatMapIterable(Users::getUsers);
	}

}
