package com.opendit.prueba.cart.infraestructure;

import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.opendit.prueba.carts.domain.CartRepository;
import com.opendit.prueba.carts.domain.entity.Cart;
import com.opendit.prueba.carts.domain.entity.Carts;
import com.opendit.prueba.carts.domain.entity.ProductCart;
import com.opendit.prueba.carts.domain.entity.ProductSummary;
import com.opendit.prueba.carts.infraestructure.CartServiceImpl;
import com.opendit.prueba.users.domain.UserService;
import com.opendit.prueba.users.domain.entity.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class CartServiceImplTest {

	@InjectMocks
	private CartServiceImpl cartService;

	@Mock
	private UserService userService;
	@Mock
	private CartRepository cartRepository;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
    void getProductsByUser_ReturnsEmptyMap_WhenNoUsersFound() {
        when(userService.getAllUsers()).thenReturn(Flux.empty());

        Mono<Map<String, ProductSummary>> result = cartService.getProductsByUser();

        StepVerifier.create(result)
            .expectNext(Collections.emptyMap())
            .verifyComplete();
    }

	@Test
	void getProductsByUser_ReturnsCorrectProductSummary_WhenUsersFound() {
		User user1 = new User();
		user1.setId(1L);
		User user2 = new User();
		user2.setId(2L);

		ProductCart product1 = new ProductCart();
		product1.setId(1L);
		product1.setTitle("product1");
		product1.setPrice(1L);
		product1.setQuantity(1L);
		product1.setTotal(200L);
		product1.setDiscountPercentage(2.0);
		product1.setDiscountedPrice(2L);
		ProductCart product2 = new ProductCart();
		product2.setId(2L);
		product2.setTitle("product2");
		product2.setPrice(100L);
		product2.setQuantity(2L);
		product2.setTotal(200L);
		product2.setDiscountPercentage(3.0);
		product2.setDiscountedPrice(2L);

		Cart cart1 = new Cart();
		cart1.setProducts(List.of(product1));
		Cart cart2 = new Cart();
		cart2.setProducts(List.of(product2));

		Carts carts1 = new Carts();
		carts1.setCarts(List.of(cart1));
		Carts carts2 = new Carts();
		carts2.setCarts(List.of(cart2));

		when(userService.getAllUsers()).thenReturn(Flux.just(user1, user2));
		when(cartRepository.findByUserId(1L)).thenReturn(Mono.just(carts1));
		when(cartRepository.findByUserId(2L)).thenReturn(Mono.just(carts2));

		Mono<Map<String, ProductSummary>> result = cartService.getProductsByUser();

		Map<String, ProductSummary> expectedMap = new HashMap<>();
		expectedMap.put("1-product1", new ProductSummary("1-product1", 1L, 8.0, 8.0));
		expectedMap.put("2-product2", new ProductSummary("2-product2", 2L, 32.0, 16.0));

		StepVerifier
			.create(result)
			.expectNext(expectedMap)
			.verifyComplete();
	}
}
