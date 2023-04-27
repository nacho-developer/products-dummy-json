package com.opendit.prueba.carts.infraestructure;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.opendit.prueba.carts.domain.CartRepository;
import com.opendit.prueba.carts.domain.CartService;
import com.opendit.prueba.carts.domain.entity.Cart;
import com.opendit.prueba.carts.domain.entity.Carts;
import com.opendit.prueba.carts.domain.entity.ProductCart;
import com.opendit.prueba.carts.domain.entity.ProductSummary;
import com.opendit.prueba.users.domain.UserService;
import com.opendit.prueba.users.domain.entity.User;

import reactor.core.publisher.GroupedFlux;
import reactor.core.publisher.Mono;

@Service
public class CartServiceImpl implements CartService {

	private final UserService userService;
	private final CartRepository cartRepository;

	public CartServiceImpl(UserService userService, CartRepository cartRepository) {
		this.userService = userService;
		this.cartRepository = cartRepository;
	}

	@Override
	public Mono<Map<String, ProductSummary>> getProductsByUser() {
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

	@Cacheable(value = "cartByUserId", key = "#userId")
	private Mono<List<ProductCart>> getCartByUserId(Long userId) {
		return cartRepository.findByUserId(userId).flatMapIterable(Carts::getCarts).map(Cart::getProducts)
				.flatMapIterable(list -> list).collectList();
	}

}
