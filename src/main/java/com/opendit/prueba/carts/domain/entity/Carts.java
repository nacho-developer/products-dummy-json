package com.opendit.prueba.carts.domain.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Carts {
	private List<Cart> carts;
	private long total;
	private long skip;
	private long limit;
}
