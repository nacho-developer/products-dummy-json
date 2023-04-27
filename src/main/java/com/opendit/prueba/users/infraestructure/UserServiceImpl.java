package com.opendit.prueba.users.infraestructure;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.opendit.prueba.users.domain.UserRepository;
import com.opendit.prueba.users.domain.UserService;
import com.opendit.prueba.users.domain.entity.User;
import com.opendit.prueba.users.domain.entity.Users;

import reactor.core.publisher.Flux;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Cacheable("allUsers")
	@Override
	public Flux<User> getAllUsers() {
		return userRepository.findAll().flatMapIterable(Users::getUsers);
	}

}
