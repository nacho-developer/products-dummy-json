package com.opendit.prueba.shared;

import java.time.Duration;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.opendit.prueba.shared.infraestrucutre.CustomException;
import com.opendit.prueba.shared.infraestrucutre.TooManyRequestsException;

import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

public class ApiClient {

    private final WebClient webClient;
    
    private static final int MAX_RETRYS = 3;
    private static final int DURATION_BAKOFF = 1;
    private static final int DURATION_MAX_BAKOFF = 10;

    public ApiClient(String baseUrl) {
        this.webClient = WebClient.builder().baseUrl(baseUrl).build();
    }
    
    public <T> Mono<T> get(String path, Class<T> responseType) {
        return webClient.get()
                .uri(path)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(responseType)
                .retryWhen(
                		Retry
                			.backoff(MAX_RETRYS, Duration.ofSeconds(DURATION_BAKOFF))
                			.maxBackoff(Duration.ofSeconds(DURATION_MAX_BAKOFF)))
                .onErrorResume(WebClientResponseException.TooManyRequests.class,
                        e -> Mono.error(new TooManyRequestsException("Too Many Requests", e)))
                .onErrorMap(throwable -> new CustomException("An error occurred while processing the request", throwable));
    }

    public <T> Mono<T> post(String path, Object body, Class<T> responseType) {
        return webClient.post()
                .uri(path)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(responseType)
                .retryWhen(
                		Retry
                			.backoff(MAX_RETRYS, Duration.ofSeconds(DURATION_BAKOFF))
                			.maxBackoff(Duration.ofSeconds(DURATION_MAX_BAKOFF)))
                .onErrorResume(WebClientResponseException.TooManyRequests.class,
                        e -> Mono.error(new TooManyRequestsException("Too Many Requests", e)))
                .onErrorMap(throwable -> new CustomException("An error occurred while processing the request", throwable));
    }

}
