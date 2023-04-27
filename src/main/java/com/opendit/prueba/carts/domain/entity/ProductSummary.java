package com.opendit.prueba.carts.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductSummary {
	private String id;
	private long quantity;
	private double totalPrice;
	private double averageDiscount;
}
