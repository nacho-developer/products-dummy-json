package com.opendit.prueba.users.domain.entity;

import java.io.IOException;

public enum Type {
	CURLY, STRAIGHT, STRANDS, VERY_CURLY, WAVY;

	public String toValue() {
		switch (this) {
		case CURLY:
			return "Curly";
		case STRAIGHT:
			return "Straight";
		case STRANDS:
			return "Strands";
		case VERY_CURLY:
			return "Very curly";
		case WAVY:
			return "Wavy";
		}
		return null;
	}

	public static Type forValue(String value) throws IOException {
		if (value.equals("Curly"))
			return CURLY;
		if (value.equals("Straight"))
			return STRAIGHT;
		if (value.equals("Strands"))
			return STRANDS;
		if (value.equals("Very curly"))
			return VERY_CURLY;
		if (value.equals("Wavy"))
			return WAVY;
		throw new IOException("Cannot deserialize Type");
	}
}
