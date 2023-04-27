package com.opendit.prueba.carts.infraestructure;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCartDTO {
	private Long id;
	private String description;
	private long quantity;
	private double price;
	private double discount;
	
	public ProductCartDTO(Long id, String description) {
		this.id = id;
		this.description = description;
	}
}
