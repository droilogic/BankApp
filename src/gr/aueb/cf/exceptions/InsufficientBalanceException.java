package gr.aueb.cf.exceptions;

import java.awt.event.WindowStateListener;

public class InsufficientBalanceException extends Exception {
    private static final long serialVersionUniqueId = 1L;

    public InsufficientBalanceException() {}

    public InsufficientBalanceException(double balance, double amount) {
        super("Insufficient balance " + balance + " for amount " + amount);
    }
}
