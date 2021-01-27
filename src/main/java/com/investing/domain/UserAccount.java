package com.investing.domain;

public class UserAccount {
    protected double balance = 0;

    public UserAccount() {
    }

    public void deposit(final double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot deposit a negative amount");
        }
        this.balance += amount;
    }

    public double getBalance() {
        return balance;
    }

    public void stockInvest(Double amount) {
        this.balance -= amount;
    }


    public void stockSell(Double amount) {
        this.balance += amount;
    }

    public double maximumAmountToWithdraw() {
        return getBalance();
    }

}
