package com.opendit.prueba.users.domain.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Company {
	private Address address;
	private String department;
	private String name;
	private String title;
}
