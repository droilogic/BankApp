package gr.aueb.cf.model;

import gr.aueb.cf.exceptions.InvalidSsnException;
import gr.aueb.cf.exceptions.NegativeAmountException;

public class OverdraftAccount extends Account {
    /**
     * default constructor
     */
    public OverdraftAccount() {}

    /**
     * overloaded constructor
     * @param holder  account owner
     * @param iban IBAN
     * @param balance initial balance
     */
    public OverdraftAccount(User holder, String iban, double balance) {
        super(holder, iban, balance);
    }

    /**
     * withdraw from account
     * @param amount to withdraw
     * @param ssn check
     * @throws NegativeAmountException invalid amount
     * @throws InvalidSsnException SSN check failed
     */
    @Override
    public void withdraw(double amount, String ssn) throws NegativeAmountException, InvalidSsnException {
        try {
            if(!isSsnValid(ssn)) throw new InvalidSsnException(ssn);
            if(amount < 0) throw new NegativeAmountException(amount);

            setBalance(getBalance() - amount);
        } catch (NegativeAmountException | InvalidSsnException e) {
            System.err.println("Error withdrawing amount " + amount);
            throw e;
        }
    }

}
