package com.opendit.prueba.products.infraestructure;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.opendit.prueba.products.domain.ProductRepository;
import com.opendit.prueba.products.domain.entity.BrandCount;
import com.opendit.prueba.products.domain.entity.CategoryCount;
import com.opendit.prueba.products.domain.entity.Product;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class ProductServiceImplTest {

	@InjectMocks
	private ProductServiceImpl productService;
	
    @Mock
    private ProductRepository productRepository;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void testGetHighestPricedProduct() {
        Product product1 = new Product();
        product1.setPrice(10L);
        product1.setTitle("Product 1");
        Product product2 = new Product();
        product2.setPrice(20L);
        product2.setTitle("Product 2");
        
        when(productRepository.findAll()).thenReturn(Flux.just(product1, product2));

        Mono<Product> highestPricedProduct = productService.getHighestPricedProduct();
        StepVerifier.create(highestPricedProduct)
                .expectNext(product2)
                .verifyComplete();
    }

    @Test
    void testGetLowestPricedProduct() {
        Product product1 = new Product();
        product1.setPrice(10L);
        Product product2 = new Product();
        product2.setPrice(20L);

        when(productRepository.findAll()).thenReturn(Flux.just(product1, product2));

        Mono<Product> lowestPricedProduct = productService.getLowestPricedProduct();
        StepVerifier.create(lowestPricedProduct)
                .expectNext(product1)
                .verifyComplete();
    }

    @Test
    void testGetAveragePrice() {
        Product product1 = new Product();
        product1.setPrice(10L);
        Product product2 = new Product();
        product2.setPrice(20L);

        when(productRepository.findAll()).thenReturn(Flux.just(product1, product2));

        Mono<Double> averagePrice = productService.getAveragePrice();
        StepVerifier.create(averagePrice)
                .expectNext(15.0)
                .verifyComplete();
    }

    @Test
    void testGetProductsByBrand() {
        Product product1 = new Product();
        product1.setBrand("Brand1");
        Product product2 = new Product();
        product2.setBrand("Brand2");

        when(productRepository.findAll()).thenReturn(Flux.just(product1, product2));

        Flux<BrandCount> productsByBrand = productService.getProductsByBrand();
        StepVerifier.create(productsByBrand)
                .expectNext(new BrandCount("Brand1", 1L))
                .expectNext(new BrandCount("Brand2", 1L))
                .verifyComplete();
    }

    @Test
    public void testGetProductsByCategory() {
        Product product1 = new Product();
        product1.setCategory("Category1");
        Product product2 = new Product();
        product2.setCategory("Category2");
        Product product3 = new Product();
        product3.setCategory("Category1");
        
        when(productRepository.findAll()).thenReturn(Flux.just(product1, product2, product3));

        Flux<CategoryCount> productsByCategory = productService.getProductsByCategory();
        StepVerifier.create(productsByCategory)
                .expectNext(new CategoryCount("Category1", 2L))
                .expectNext(new CategoryCount("Category2", 1L))
                .verifyComplete();
    }
}
