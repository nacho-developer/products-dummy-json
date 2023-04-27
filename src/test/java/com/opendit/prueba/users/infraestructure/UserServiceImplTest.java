package com.opendit.prueba.users.infraestructure;

import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.opendit.prueba.users.domain.UserRepository;
import com.opendit.prueba.users.domain.entity.User;
import com.opendit.prueba.users.domain.entity.Users;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllUsers() {
        User user1 = new User();
        user1.setId(1L);
        user1.setFirstName("John");
        user1.setLastName("Doe");
        user1.setAge(25L);

        User user2 = new User();
        user2.setId(2L);
        user2.setFirstName("Jane");
        user2.setLastName("Doe");
        user2.setAge(30L);

        Users users = new Users();
        users.setUsers(List.of(user1, user2));

        when(userRepository.findAll()).thenReturn(Mono.just(users));

        Flux<User> allUsers = userService.getAllUsers();

        StepVerifier.create(allUsers)
            .expectNext(user1)
            .expectNext(user2)
            .verifyComplete();
    }
}
