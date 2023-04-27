package com.opendit.prueba.config;

import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.WebFluxConfigurer;

import com.opendit.prueba.shared.infraestrucutre.ApiConnectionExceptionHandler;

@Configuration
public class WebFluxConfig implements WebFluxConfigurer {
 
    @Bean
    ErrorWebExceptionHandler errorWebExceptionHandler() {
        return new ApiConnectionExceptionHandler();
    }
}
