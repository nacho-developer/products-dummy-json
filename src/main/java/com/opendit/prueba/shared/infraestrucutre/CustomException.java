package com.opendit.prueba.shared.infraestrucutre;

public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 870911346636463355L;

	public CustomException(String message, Throwable cause) {
		super(message, cause);
	}

}
