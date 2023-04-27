package com.opendit.prueba.products.infraestructure;

import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.opendit.prueba.products.domain.ProductRepository;
import com.opendit.prueba.products.domain.entity.BrandCount;
import com.opendit.prueba.products.domain.entity.CategoryCount;
import com.opendit.prueba.products.domain.entity.Product;
import com.opendit.prueba.products.domain.entity.Products;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetHighestPricedProduct() {
        Product product1 = new Product();
        product1.setPrice(10L);
        Product product2 = new Product();
        product2.setPrice(20L);
        
        Products products = new Products();
        products.setProducts(List.of(product1, product2));

        when(productRepository.findAll()).thenReturn(Mono.just(products));

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
        
        Products products = new Products();
        products.setProducts(List.of(product1, product2));

        when(productRepository.findAll()).thenReturn(Mono.just(products));

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
        
        Products products = new Products();
        products.setProducts(List.of(product1, product2));

        when(productRepository.findAll()).thenReturn(Mono.just(products));

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
        
        Products products = new Products();
        products.setProducts(List.of(product1, product2));

        when(productRepository.findAll()).thenReturn(Mono.just(products));

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
        
        Products products = new Products();
        products.setProducts(List.of(product1, product2, product3));
        
        when(productRepository.findAll()).thenReturn(Mono.just(products));

        Flux<CategoryCount> productsByCategory = productService.getProductsByCategory();
        StepVerifier.create(productsByCategory)
                .expectNext(new CategoryCount("Category1", 2L))
                .expectNext(new CategoryCount("Category2", 1L))
                .verifyComplete();
    }
}
