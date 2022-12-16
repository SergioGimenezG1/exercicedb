package com.example.exercicedb.middleware.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class MiddlewareException extends RuntimeException {

	private String defaultMessage;

	public MiddlewareException(String message) {
		super(message);
	}
	
	public MiddlewareException(String message, String defaultMessage) {
		super(message);
		this.defaultMessage = defaultMessage;
	}

	public MiddlewareException(String message, Throwable cause) {
		super(message, cause);
	}

	public String getDefaultMessage() {
		return defaultMessage;
	}

	public void setDefaultMessage(String defaultMessage) {
		this.defaultMessage = defaultMessage;
	}

}
