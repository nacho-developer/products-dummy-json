package com.opendit.prueba.carts.domain.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCart {
	private long id;
	private String title;
	private long price;
	private long quantity;
	private long total;
	private double discountPercentage;
	private long discountedPrice;
}
