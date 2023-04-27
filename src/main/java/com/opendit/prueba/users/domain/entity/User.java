package com.opendit.prueba.users.domain.entity;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
	private long id;
	private String firstName;
	private String lastName;
	private String maidenName;
	private long age;
	// private Gender gender;
	private String email;
	private String phone;
	private String username;
	private String password;
	private LocalDate birthDate;
	private String image;
	private String bloodGroup;
	private long height;
	private double weight;
	// private EyeColor eyeColor;
//	private Hair hair;
	private String domain;
	private String ip;
	private Address address;
	private String macAddress;
	private String university;
	private Bank bank;
	private Company company;
	private String ein;
	private String ssn;
	private String userAgent;
}
