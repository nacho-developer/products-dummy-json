package com.opendit.prueba.carts.application;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.opendit.prueba.carts.domain.CartService;
import com.opendit.prueba.carts.domain.entity.ProductSummary;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

/**
 * This class represents the REST controller for the Cart API.
 * It provides endpoints for retrieving cart data.
 * 
 * @author Nacho Dominguez
 */
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/carts")
@Api(value = "Cart Controller", tags = { "Cart API" })
public class CartController {
	
	private final CartService cartService;
	
	/**
	 * This method retrieves all products by user.
	 * 
	 * @return a {@link Mono} containing a {@link Map} of {@link ProductSummaty} objects keyed by their IDs.
	 */
	@ApiOperation(value = "Get all products by user")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successful operation", response = Map.class)
    })
    @GetMapping("/products-by-users")
    public Mono<Map<String, ProductSummary>> getProductsByUser() {
		return cartService.getProductsByUser();
	}

}
