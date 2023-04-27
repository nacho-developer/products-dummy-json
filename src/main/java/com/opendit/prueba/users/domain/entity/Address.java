package com.opendit.prueba.users.domain.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {
	private String address;
	private String city;
	private Coordinates coordinates;
	private String postalCode;
	private String state;
}
