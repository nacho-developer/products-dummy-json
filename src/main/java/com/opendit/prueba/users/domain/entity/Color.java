package com.opendit.prueba.users.domain.entity;

import java.io.IOException;

public enum Color {
	AUBURN, BLACK, BLOND, BROWN, CHESTNUT;

	public String toValue() {
		switch (this) {
		case AUBURN:
			return "Auburn";
		case BLACK:
			return "Black";
		case BLOND:
			return "Blond";
		case BROWN:
			return "Brown";
		case CHESTNUT:
			return "Chestnut";
		}
		return null;
	}

	public static Color forValue(String value) throws IOException {
		if (value.equals("Auburn"))
			return AUBURN;
		if (value.equals("Black"))
			return BLACK;
		if (value.equals("Blond"))
			return BLOND;
		if (value.equals("Brown"))
			return BROWN;
		if (value.equals("Chestnut"))
			return CHESTNUT;
		throw new IOException("Cannot deserialize Color");
	}
}
