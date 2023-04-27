package com.opendit.prueba.carts.domain.entity;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cart {
	private long id;
	private List<ProductCart> products;
	private long total;
	private long discountetal;
	private long userID;
	private long totalProducts;
	private long totalQuantity;
}
