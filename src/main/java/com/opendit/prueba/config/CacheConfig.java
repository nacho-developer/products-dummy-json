package com.opendit.prueba.config;

import java.util.List;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableCaching
@Slf4j
public class CacheConfig {

	@Bean
	CacheManager cacheManager() {
		log.info("Init cache manager");
	    ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager();
	    cacheManager.setCacheNames(List.of("allProducts", "highestPricedProductCache", "lowestPricedProductCache",
	            "averagePricedProductCache", "productsByBrandCache", "productsByCategoryCache", "allUsers",
	            "findByUserId"));
	    
	    return cacheManager;
	}

}
