package com.opendit.prueba.users.domain.entity;

import java.io.IOException;

public enum EyeColor {
	AMBER, BLUE, BROWN, GRAY, GREEN;

	public String toValue() {
		switch (this) {
		case AMBER:
			return "Amber";
		case BLUE:
			return "Blue";
		case BROWN:
			return "Brown";
		case GRAY:
			return "Gray";
		case GREEN:
			return "Green";
		}
		return null;
	}

	public static EyeColor forValue(String value) throws IOException {
		if (value.equals("Amber"))
			return AMBER;
		if (value.equals("Blue"))
			return BLUE;
		if (value.equals("Brown"))
			return BROWN;
		if (value.equals("Gray"))
			return GRAY;
		if (value.equals("Green"))
			return GREEN;
		throw new IOException("Cannot deserialize EyeColor");
	}
}
