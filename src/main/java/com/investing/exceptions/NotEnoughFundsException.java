package com.investing.exceptions;

public class NotEnoughFundsException extends BankException {
	
	public NotEnoughFundsException( String message) {
        super(message);
    }

}
