package br.com.carv.exception;

import java.io.Serial;

public class InvalidArgumentsException extends IllegalArgumentException {

	@Serial
	private static final long serialVersionUID = 0L;
	
	public InvalidArgumentsException(String message) {
		super(message);
	}
}
