package com.investing.exceptions;

public class NotEnoughFundsException extends BankException {
	
	private static final long serialVersionUID = -3034651278778929257L;
	private double balance;
	private double amount;
	
	public NotEnoughFundsException( double balance, double amount, String message) {
        super(message);
        this.balance = balance;
        this.amount = Math.round(amount * 100) / 100d;
    }
    
    public double getBalance() {
		return balance;
	}

    public double getAmount() {
        return amount;
    }

}
