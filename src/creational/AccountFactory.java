package creational;

import model.Account;

public class AccountFactory {

    public static AccountFactory accountFactory;

    public AccountFactory() {
    }

    public Account createSimpleAccount(String fullName, String username, String password, String phoneNumber){
        return new AccountBuilder(new Account(fullName,username,password,phoneNumber)).createAccount();
    }

    public Account createDepositAccount(String fullName, String username, String password, String phoneNumber){
        return new AccountBuilder(new Account(fullName,username,password,phoneNumber)).isDepositAccount().createAccount();
    }

    public Account createLoanAccount(String fullName, String username, String password, String phoneNumber,int amount){
        return new AccountBuilder(new Account(fullName,username,password,phoneNumber)).isLoanAccount(amount).createAccount();
    }

}
