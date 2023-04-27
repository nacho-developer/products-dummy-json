package com.opendit.prueba.shared.infraestrucutre;

import java.net.ConnectException;

import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class ApiConnectionExceptionHandler implements ErrorWebExceptionHandler  {

	@Override
	public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
		if (ex instanceof WebClientResponseException) {
			return handleWebClientResponseException(exchange, (WebClientResponseException) ex);
		} else if (ex instanceof ConnectException) {
			return handleConnectException(exchange, (ConnectException) ex);
		} else {
			
			return handleOtherException(exchange, ex);
		}
	}

	private Mono<Void> handleWebClientResponseException(ServerWebExchange exchange, WebClientResponseException ex) {
		HttpStatus httpStatus = ex.getStatusCode();
		String message = ex.getResponseBodyAsString();
		exchange.getResponse().setStatusCode(httpStatus);
		exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
		return exchange.getResponse()
				.writeWith(Mono.just(exchange.getResponse().bufferFactory().wrap(message.getBytes())));
	}

	private Mono<Void> handleConnectException(ServerWebExchange exchange, ConnectException ex) {
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		String message = "Error connecting to the server";
		exchange.getResponse().setStatusCode(httpStatus);
		exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
		return exchange.getResponse()
				.writeWith(Mono.just(exchange.getResponse().bufferFactory().wrap(message.getBytes())));
	}

	private Mono<Void> handleOtherException(ServerWebExchange exchange, Throwable ex) {
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		String message = "An error occurred while processing the request";
		exchange.getResponse().setStatusCode(httpStatus);
		exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
		return exchange.getResponse()
				.writeWith(Mono.just(exchange.getResponse().bufferFactory().wrap(message.getBytes())));
	}

}
