package com.investing.exceptions;

public class StockExistsException extends BankException {
    private static final long serialVersionUID = -8368249553360028667L;

    public StockExistsException(String message) {
        super(message);
    }
}
