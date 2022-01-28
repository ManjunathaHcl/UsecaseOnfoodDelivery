package com.homelyfooduser.exception;

import net.bytebuddy.implementation.bind.annotation.Super;

public class InvalidDataException extends RuntimeException {

	public InvalidDataException(String message) {
		super(message);
	}

}
