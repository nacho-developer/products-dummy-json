package com.opendit.prueba.config;

import java.util.List;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {

	@Bean
	CacheManager cacheManager() {
	    ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager();
	    cacheManager.setCacheNames(List.of("allProducts", "highestPricedProductCache", "lowestPricedProductCache",
	            "averagePricedProductCache", "productsByBrandCache", "productsByCategoryCache", "allUsers",
	            "cartByUserId"));
	    

	    // Set TTL for all caches to 10 minutes
//	    for (String cacheName : cacheManager.getCacheNames()) {
//	        Cache cache = cacheManager.getCache(cacheName);
//	        Object cacheValue = cache.get(cacheName).get(); // obtain the actual value from the ValueWrapper
//	        cache.put(cacheName, cacheValue); // use cacheValue as the value argument
//	    }
        
	    return cacheManager;
	}

}
