package com.opendit.prueba.carts.domain.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Cart {
	private long id;
	private List<ProductCart> products;
	private long total;
	private long discountetal;
	private long userID;
	private long totalProducts;
	private long totalQuantity;
}
