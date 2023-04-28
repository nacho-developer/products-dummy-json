package com.opendit.prueba.products.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CategoryCount {

	private String category;
	private long count;

}
