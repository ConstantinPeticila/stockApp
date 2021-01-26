package com.investing.domain;

import com.investing.exceptions.NotEnoughFundsException;
import java.util.Objects;

public class UserAccount{
    protected double balance = 0;

    public UserAccount() {
    }

    public void deposit(final double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot deposit a negative amount");
        }
        this.balance += amount;
    }

    public void withdraw(final double amount) throws NotEnoughFundsException {
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot withdraw a negative amount");
        }

        if (amount > maximumAmountToWithdraw()) {
            throw new NotEnoughFundsException(balance, amount, "Requested amount exceeds the maximum amount to withdraw");
        }
        this.balance -= amount;
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

    public long decimalValue() {
        return Math.round(this.balance);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAccount)) return false;
        UserAccount that = (UserAccount) o;
        return Double.compare(that.getBalance(), getBalance()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBalance());
    }

}
