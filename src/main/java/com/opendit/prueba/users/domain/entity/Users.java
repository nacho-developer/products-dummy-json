package com.opendit.prueba.users.domain.entity;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Users {

	private List<User> users;
	private long total;
	private long skip;
	private long limit;

}
