package com.opendit.prueba.carts.domain.entity;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Carts {
	private List<Cart> carts;
	private long total;
	private long skip;
	private long limit;
}
