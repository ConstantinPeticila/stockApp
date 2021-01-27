package com.investing.exceptions;

public class StockExistsException extends BankException {

    public StockExistsException(String message) {
        super(message);
    }
}
