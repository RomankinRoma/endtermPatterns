package creational;

import behavioral.state.StateImpl;
import model.Account;
import model.DepLoan;

public class AccountBuilder {
    private Account account;


    public AccountBuilder(Account account) {
        this.account = account;
    }

    public AccountBuilder isDepositAccount(){
        account.setDepLoan(new DepLoan(0,new StateImpl(),true,false));
        return this;
    }

    public AccountBuilder isLoanAccount(int amount){
        account.setDepLoan(new DepLoan(amount,new StateImpl(),false,true));
        return this;
    }

    public Account createAccount(){
        return new Account(this);
    };

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
