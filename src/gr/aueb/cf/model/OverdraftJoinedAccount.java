package gr.aueb.cf.model;

import gr.aueb.cf.exceptions.InsufficientBalanceException;
import gr.aueb.cf.exceptions.InvalidSsnException;
import gr.aueb.cf.exceptions.NegativeAmountException;

public class OverdraftJoinedAccount extends JoinedAccount {

    public OverdraftJoinedAccount() {}

    public OverdraftJoinedAccount(User holder, String iban, double balance, User secondHolder) {
        super(holder, iban, balance, secondHolder);
    }

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
