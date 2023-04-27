package com.opendit.prueba.products.infraestructure;

import java.util.Comparator;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.opendit.prueba.products.domain.ProductRepository;
import com.opendit.prueba.products.domain.ProductService;
import com.opendit.prueba.products.domain.entity.BrandCount;
import com.opendit.prueba.products.domain.entity.CategoryCount;
import com.opendit.prueba.products.domain.entity.Product;
import com.opendit.prueba.products.domain.entity.Products;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;

	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Cacheable("allProducts")
	@Override
	public Flux<Product> getAllProducts() {
		return productRepository.findAll().flatMapIterable(Products::getProducts);
	}

	@Cacheable("highestPricedProductCache")
	@Override
	public Mono<Product> getHighestPricedProduct() {
		return getAllProducts()
				.reduce((p1, p2) -> p1.getPrice() > p2.getPrice() ? p1 : p2);

	}

	@Cacheable("lowestPricedProductCache")
	@Override
	public Mono<Product> getLowestPricedProduct() {
		return getAllProducts()
				.reduce((p1, p2) -> p1.getPrice() < p2.getPrice() ? p1 : p2);
	}

	@Cacheable("averagePricedProductCache")
	@Override
	public Mono<Double> getAveragePrice() {
		Flux<Product> products = getAllProducts();
		return products
				.map(Product::getPrice)
				.reduce(0.0, (p1, p2) -> p1 + p2)
				.zipWith(products.count())
				.map(tuple -> tuple.getT1() / (double) tuple.getT2());
	}

	@Cacheable("productsByBrandCache")
	@Override
	public Flux<BrandCount> getProductsByBrand() {
		return getAllProducts()
				.groupBy(Product::getBrand)
				.flatMap(group -> 
					group.count()
						.map(count -> new BrandCount(group.key(), count)))
				.sort(Comparator.comparing(BrandCount::getBrand));
	}

	@Cacheable("productsByCategoryCache")
	@Override
	public Flux<CategoryCount> getProductsByCategory() {
		return getAllProducts()
				.groupBy(Product::getCategory)
				.flatMap(group -> 
					group.count()
						.map(count -> new CategoryCount(group.key(), count)))
				.sort(Comparator.comparing(CategoryCount::getCategory));
	}
}
