package gr.aueb.cf.model;

public class JoinedAccount extends Account {
    private User secondHolder = new User();

    /**
     * default constructor
     */
    public JoinedAccount() {}

    /**
     * overloaded constructor
     * @param holder
     * @param iban
     * @param balance
     * @param secondHolder
     */
    public JoinedAccount(User holder, String iban, double balance, User secondHolder) {
        super(holder, iban, balance);
        this.secondHolder = secondHolder;
    }

    public User getSecondHolder() {
        return secondHolder;
    }

    public void setSecondHolder(User secondHolder) {
        this.secondHolder = secondHolder;
    }

    @Override
    public String toString() {
        return "JoinedAccount{firstHolder=" + getHolder() +
                ", secondHolder=" + secondHolder + ", IBAN: " + getIban() +
                ", balance: " + getBalance() + "}";
    }

    @Override
    protected boolean isSsnValid(String ssn) {
        return super.isSsnValid(ssn) || secondHolder.getSsn().equals(ssn);
    }
}
