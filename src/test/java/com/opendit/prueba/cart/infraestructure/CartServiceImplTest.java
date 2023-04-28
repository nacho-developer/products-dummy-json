package com.opendit.prueba.cart.infraestructure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Collections;
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
    void testGetProductsByUserWhenNoUsersFound() {
        when(userService.getAllUsers()).thenReturn(Flux.empty());

        Mono<Map<String, ProductSummary>> result = cartService.getProductsByUser();

        StepVerifier.create(result)
            .expectNext(Collections.emptyMap())
            .verifyComplete();
    }
	
	@Test
    void testGetProductsByUser() {
		User user1 = new User();
		user1.setId(1L);
		User user2 = new User();
		user2.setId(2L);
        
        List<ProductCart> user1Cart = List.of(
            new ProductCart(1L, "Product 1", 10L, 2L, 20L, 0.0, 20L),
            new ProductCart(2L, "Product 2", 20L, 1L, 20L, 0.2, 16L)
        );
        
        List<ProductCart> user2Cart = List.of(
            new ProductCart(1L, "Product 1", 10L, 1L, 10L, 0.1, 9L),
            new ProductCart(3L, "Product 3", 30L, 3L, 90L, 0.3, 63L)
        );
        
        List<Cart> cart1 = List.of(
        	Cart.builder().products(user1Cart).build()
        );
        
        List<Cart> cart2 = List.of(
        	Cart.builder().products(user2Cart).build()
        );
        
        when(userService.getAllUsers()).thenReturn(Flux.just(user1, user2));
        
        when(cartRepository.findByUserId(1L)).thenReturn(Mono.just(Carts.builder().carts(cart1).build()));
        when(cartRepository.findByUserId(2L)).thenReturn(Mono.just(Carts.builder().carts(cart2).build()));
        
        Map<String, ProductSummary> result = cartService.getProductsByUser().block();
        
        assertNotNull(result);
        assertEquals(3, result.size());
        assertTrue(result.containsKey("1-Product 1"));
        assertTrue(result.containsKey("2-Product 2"));
        assertTrue(result.containsKey("3-Product 3"));
        
        ProductSummary summary1 = result.get("1-Product 1");
        assertNotNull(summary1);
        assertEquals(3L, summary1.getQuantity());
        assertEquals(20L, summary1.getTotalPrice(), 0.0);
        assertEquals(14.5, summary1.getAverageDiscount(), 0.0);
        
        ProductSummary summary2 = result.get("2-Product 2");
        assertNotNull(summary2);
        assertEquals(1L, summary2.getQuantity());
        assertEquals(20L, summary2.getTotalPrice(), 0.0);
        assertEquals(16, summary2.getAverageDiscount(), 0.0);
        
        ProductSummary summary3 = result.get("3-Product 3");
        assertNotNull(summary3);
        assertEquals(3L, summary3.getQuantity());
        assertEquals(30L, summary3.getTotalPrice(), 0.0);
        assertEquals(63, summary3.getAverageDiscount(), 0.0);
    }
}
