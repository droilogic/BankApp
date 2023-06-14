package gr.aueb.cf.exceptions;

public class InvalidSsnException extends Exception {
    private static final long serialVersionUniqueId = 1L;

    public InvalidSsnException(String ssn) {
        super("SSN " + ssn + " is invalid!");
    }
}
