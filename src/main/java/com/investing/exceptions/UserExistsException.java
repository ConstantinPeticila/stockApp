package com.investing.exceptions;

public class UserExistsException extends BankException {
	private static final long serialVersionUID = -8368249553360028667L;

	public UserExistsException(String message) {
		super(message);
	}

}
