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

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
@Api(value = "Product Controller", tags = { "Product API" })
public class ProductController {

	private final ProductService productService;

	@GetMapping("/max-price")
	@ApiOperation(value = "Get highest priced product", response = Product.class)
	public Mono<Product> getHighestPricedProduct() {
		return productService.getHighestPricedProduct();
	}

	@GetMapping("/min-price")
	@ApiOperation(value = "Get lowest priced product", response = Product.class)
	public Mono<Product> getLowestPricedProduct() {
		return productService.getLowestPricedProduct();
	}

	@GetMapping("/average-price")
	@ApiOperation(value = "Get average product price", response = Double.class)
	public Mono<Double> getAveragePrice() {
		return productService.getAveragePrice();
	}

	@GetMapping("/products-by-brand")
	@ApiOperation(value = "Get products by brand", response = BrandCount.class, responseContainer = "List")
	public Flux<BrandCount> getProductsByBrand() {
		return productService.getProductsByBrand();
	}

	@GetMapping("/products-by-category")
	@ApiOperation(value = "Get products by category", response = CategoryCount.class, responseContainer = "List")
	public Flux<CategoryCount> getProductsByCategory() {
		return productService.getProductsByCategory();
	}
}

