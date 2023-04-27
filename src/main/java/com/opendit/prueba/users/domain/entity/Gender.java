package com.opendit.prueba.users.domain.entity;

import java.io.IOException;

public enum Gender {
	FEMALE, MALE;

	public String toValue() {
		switch (this) {
		case FEMALE:
			return "female";
		case MALE:
			return "male";
		}
		return null;
	}

	public static Gender forValue(String value) throws IOException {
		if (value.equals("female"))
			return FEMALE;
		if (value.equals("male"))
			return MALE;
		throw new IOException("Cannot deserialize Gender");
	}
}
