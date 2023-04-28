package com.opendit.prueba.products.domain.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
	private long id;
	private String title;
	private String description;
	private long price;
	private double discountPercentage;
	private double rating;
	private long stock;
	private String brand;
	private String category;
	private String thumbnail;
	private String[] images;
	
}
