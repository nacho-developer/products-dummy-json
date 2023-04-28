package com.opendit.prueba.products.infraestructure;

import java.util.Comparator;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.opendit.prueba.products.domain.ProductRepository;
import com.opendit.prueba.products.domain.ProductService;
import com.opendit.prueba.products.domain.entity.BrandCount;
import com.opendit.prueba.products.domain.entity.CategoryCount;
import com.opendit.prueba.products.domain.entity.Product;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Implementation of the ProductService interface that provides methods for
 * retrieving and processing product data. This class uses caching to optimize
 * performance and reduce API calls.
 * 
 * @author Nacho Dominguez
 *
 */
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;

	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	/**
	 * Retrieves the highest priced product.
	 * @return a {@link Mono} of {@link Product}.
	 */
	@Cacheable("highestPricedProductCache")
	@Override
	public Mono<Product> getHighestPricedProduct() {
		log.info("Method: getHighestPricedProduct");
		return productRepository.findAll()
				.reduce((p1, p2) -> p1.getPrice() > p2.getPrice() ? p1 : p2);
	}

	/**
	 * Retrieves the lowest priced product.
	 * @return a {@link Mono} of {@link Product}.
	 */
	@Cacheable("lowestPricedProductCache")
	@Override
	public Mono<Product> getLowestPricedProduct() {
		log.info("Method: getLowestPricedProduct");
		return productRepository.findAll()
				.reduce((p1, p2) -> p1.getPrice() < p2.getPrice() ? p1 : p2);
	}

	/**
	 * Retrieves the average price of all products.
	 * @return a {@link Mono} of {@link Double}.
	 */
	@Cacheable("averagePricedProductCache")
	@Override
	public Mono<Double> getAveragePrice() {
		log.info("Method: getAveragePrice");
		Flux<Product> products = productRepository.findAll();
		return products
				.map(Product::getPrice)
				.reduce(0.0, (p1, p2) -> p1 + p2)
				.zipWith(products.count())
				.map(tuple -> tuple.getT1() / (double) tuple.getT2());
	}

	/**
	 * Retrieves the count of products for each brand.
	 * @return a {@link Flux} of {@link BrandCount}.
	 */
	@Cacheable("productsByBrandCache")
	@Override
	public Flux<BrandCount> getProductsByBrand() {
		log.info("Method: getProductsByBrand");
		return productRepository.findAll()
				.groupBy(Product::getBrand)
				.flatMap(group -> 
					group.count()
						.map(count -> new BrandCount(group.key(), count)))
				.sort(Comparator.comparing(BrandCount::getBrand));
	}

	/**
	 * Retrieves the count of products for each category.
	 * @return a {@link Flux} of {@link CategoryCount}.
	 */
	@Cacheable("productsByCategoryCache")
	@Override
	public Flux<CategoryCount> getProductsByCategory() {
		log.info("Method: getProductsByCategory");
		return productRepository.findAll()
				.groupBy(Product::getCategory)
				.flatMap(group -> 
					group.count()
						.map(count -> new CategoryCount(group.key(), count)))
				.sort(Comparator.comparing(CategoryCount::getCategory));
	}
}
