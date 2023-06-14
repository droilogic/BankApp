package gr.aueb.cf.model;

import gr.aueb.cf.exceptions.InsufficientBalanceException;
import gr.aueb.cf.exceptions.InvalidSsnException;
import gr.aueb.cf.exceptions.NegativeAmountException;

import java.util.Objects;

public class Account extends IdentifiableEntity {
    private User holder;
    private String iban;
    private double balance;

    public Account() {}

    public Account(User holder, String iban, double balance) {
        this.holder = holder;
        this.iban = iban;
        this.balance = balance;
    }

    public User getHolder() {
        return holder;
    }

    public void setHolder(User holder) {
        this.holder = holder;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "holder=" + holder +
                ", iban='" + iban + '\'' +
                ", balance=" + balance +
                '}';
    }

    // Public API

    /**
     * deposits amount
     * @param amount to deposit
     * @throws NegativeAmountException when amount < 0
     */
    public void deposit(double amount) throws NegativeAmountException {
        try {
            if(amount < 0) throw new NegativeAmountException(amount);
            balance += amount;
        } catch (NegativeAmountException e) {
            System.err.println("Error: Negative amount");
            throw e;
        }
    }

    /**
     * withdraw amount
     * @param amount to withdraw
     * @param ssn for validation
     * @throws InsufficientBalanceException when balance < amount
     * @throws NegativeAmountException when amount < 0
     * @throws InvalidSsnException when SSN check fails
     */
    public void withdraw(double amount, String ssn) throws InsufficientBalanceException, NegativeAmountException, InvalidSsnException {
        try {
            if(!isSsnValid(ssn)) throw new InvalidSsnException(ssn);
            if(amount < 0) throw new NegativeAmountException(amount);
            if(amount > balance) throw new InsufficientBalanceException(balance, amount);

            balance -= amount;
        } catch (InsufficientBalanceException | NegativeAmountException | InvalidSsnException e) {
            System.err.println("Error withdrawing amount " + amount);
            throw e;
            // we can also have one catch per exception
        }
    }

    /**
     * SSN validation
     * @param ssn to check
     * @return true if valid
     */
    protected  boolean isSsnValid(String ssn) {
        if(ssn == null || holder.getSsn() == null) return false;
        return holder.getSsn().equals(ssn);
    }
}
