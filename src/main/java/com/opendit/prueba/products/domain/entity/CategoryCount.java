package com.opendit.prueba.products.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CategoryCount {

	private String category;
	private long count;

}
