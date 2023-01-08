package com.eCommerce.eCommerce.exception;

public class UserDetailsNotFoundException extends RuntimeException {
	
	public UserDetailsNotFoundException(String message) {
		super(message);
	}
}
