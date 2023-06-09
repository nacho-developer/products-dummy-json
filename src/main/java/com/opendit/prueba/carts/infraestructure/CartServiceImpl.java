package com.opendit.prueba.carts.infraestructure;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.opendit.prueba.carts.domain.CartRepository;
import com.opendit.prueba.carts.domain.CartService;
import com.opendit.prueba.carts.domain.entity.Cart;
import com.opendit.prueba.carts.domain.entity.Carts;
import com.opendit.prueba.carts.domain.entity.ProductCart;
import com.opendit.prueba.carts.domain.entity.ProductSummary;
import com.opendit.prueba.users.domain.UserService;
import com.opendit.prueba.users.domain.entity.User;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.GroupedFlux;
import reactor.core.publisher.Mono;

/**
 * This class implements the CartService interface and defines the methods
 * related to carts. It retrieves information about the products in the carts of
 * all users, and retrieves the cart of a specific user using their user ID.
 * 
 * @author Nacho Dominguez
 */
@Service
@Slf4j
public class CartServiceImpl implements CartService {

	private final UserService userService;
	private final CartRepository cartRepository;

	public CartServiceImpl(UserService userService, CartRepository cartRepository) {
		this.userService = userService;
		this.cartRepository = cartRepository;
	}

	/**
	 * Retrieves information about the products in the carts of all users.
	 * 
	 * @return a {@link Mono} of a {@link Map} of {@link ProductSummary} objects, where the key is the product ID.
	 */
	@Override
	public Mono<Map<String, ProductSummary>> getProductsByUser() {
		log.info("Method: getProductsByUser");
		return userService.getAllUsers()
		        .flatMapSequential((User user) -> getCartByUserId(user.getId()))
		        .flatMapIterable(list -> list)
		        .groupBy(ProductCart::getId)
				.flatMap((GroupedFlux<Long, ProductCart> group) -> 
		        	group.collectList()
		        		.map(products -> 
				        	new ProductSummary(
				                group.key() + "-" + products.stream().map(ProductCart::getTitle).findAny().get(),
				                products.stream().mapToLong(ProductCart::getQuantity).sum(),
				                products.stream().mapToDouble(ProductCart::getPrice).sum(),
				                products.stream().mapToDouble(ProductCart::getDiscountedPrice).average().orElse(0.0))
			        )
		        )
				.collectMap(ProductSummary::getId, Function.identity());
	}

	/**
	 * Retrieves the cart of a specific user by their ID from the database.
	 * 
	 * The method is annotated with @Cacheable, which means that the result of this method is cached 
	 * and returned from the cache if the same request is made again.
	 * 
	 * @param userId the ID of the user whose cart is to be retrieved.
	 * @return a {@link Mono} of a {@link List} of {@link ProductCart} objects representing the products in the user's cart.
	 */
	private Mono<List<ProductCart>> getCartByUserId(Long userId) {
		log.info("Method: getCartByUserId with userId=" + userId);
		return cartRepository.findByUserId(userId).flatMapIterable(Carts::getCarts).map(Cart::getProducts)
				.flatMapIterable(list -> list).collectList();
	}

}
