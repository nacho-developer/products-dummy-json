package com.opendit.prueba.carts.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductSummary {
	private String id;
	private long quantity;
	private double totalPrice;
	private double averageDiscount;
}
