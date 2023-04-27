package com.opendit.prueba.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ConfigurationProperties(prefix = "rest")
@Getter
@Setter
@Component
public class RestProperties {

	private DummyJson dummyJson;

	@NoArgsConstructor
	@Getter
	@Setter
	public static class DummyJson {
		private String urlBase;
		private String pathUsers;
		private String pathCarts;
		private String pathProducts;
	}

}
