package model;

import behavioral.state.State;

public class DepLoan {
    private int amount;
    private State state;
    private boolean deposit;
    private boolean loan;

    public DepLoan(int amount,State state,boolean deposit, boolean loan) {
        this.amount = amount;
        this.state = state.created();
        this.deposit = deposit;
        this.loan = loan;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public boolean isDeposit() {
        return deposit;
    }

    public void setDeposit(boolean deposit) {
        this.deposit = deposit;
    }

    public boolean isLoan() {
        return loan;
    }

    public void setLoan(boolean loan) {
        this.loan = loan;
    }

    @Override
    public String toString() {
        return "DepLoan{" +
                "amount=" + amount +
                ", state=" + state.getStateString() +
                ", deposit=" + deposit +
                ", loan=" + loan +
                '}';
    }
}
