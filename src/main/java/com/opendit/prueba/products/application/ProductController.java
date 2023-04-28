package com.opendit.prueba.products.application;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.opendit.prueba.products.domain.ProductService;
import com.opendit.prueba.products.domain.entity.BrandCount;
import com.opendit.prueba.products.domain.entity.CategoryCount;
import com.opendit.prueba.products.domain.entity.Product;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * This class represents the REST controller for the Product API.
 * It provides endpoints for retrieving product data.
 * 
 * @author Nacho Dominguez
 */
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
@Api(value = "Product Controller", tags = { "Product API" })
public class ProductController {

	private final ProductService productService;

	/**
	 * Returns the product with the highest price.
	 * 
	 * @return a Mono with the highest priced product
	 */
	@GetMapping("/max-price")
	@ApiOperation(value = "Get highest priced product", response = Product.class)
	public Mono<Product> getHighestPricedProduct() {
		return productService.getHighestPricedProduct();
	}
	
	/**
	 * Returns the product with the lowest price.
	 * 
	 * @return a Mono with the lowest priced product
	 */
	@GetMapping("/min-price")
	@ApiOperation(value = "Get lowest priced product", response = Product.class)
	public Mono<Product> getLowestPricedProduct() {
		return productService.getLowestPricedProduct();
	}

	/**
	 * Returns the average price of all products.
	 * 
	 * @return a Mono with the average price
	 */
	@GetMapping("/average-price")
	@ApiOperation(value = "Get average product price", response = Double.class)
	public Mono<Double> getAveragePrice() {
		return productService.getAveragePrice();
	}

	/**
	 * Returns a Flux with the count of products by brand.
	 * 
	 * @return a Flux with the count of products by brand
	 */
	@GetMapping("/products-by-brand")
	@ApiOperation(value = "Get products by brand", response = BrandCount.class, responseContainer = "List")
	public Flux<BrandCount> getProductsByBrand() {
		return productService.getProductsByBrand();
	}

	/**
	 * Returns a Flux with the count of products by category.
	 * 
	 * @return a Flux with the count of products by category
	 */
	@GetMapping("/products-by-category")
	@ApiOperation(value = "Get products by category", response = CategoryCount.class, responseContainer = "List")
	public Flux<CategoryCount> getProductsByCategory() {
		return productService.getProductsByCategory();
	}
}

