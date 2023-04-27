package com.opendit.prueba.shared.infraestrucutre;

public class TooManyRequestsException extends RuntimeException {

	private static final long serialVersionUID = 870911346636463355L;

	public TooManyRequestsException(String message, Throwable cause) {
		super(message, cause);
	}

}
