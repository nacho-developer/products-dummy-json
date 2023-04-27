package com.opendit.prueba.products.domain.entity;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Products {
	private List<Product> products;
	private long total;
	private long skip;
	private long limit;
}
