package model;

import behavioral.observer.NationalBankPublisher;
import behavioral.observer.Publisher;
import behavioral.observer.Subscriber;
import behavioral.state.State;
import creational.AccountBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Account implements Subscriber {
    private String fullName;
    private String username;
    private String password;
    private int amount;
    private String phoneNumber;
    private DepLoan depLoan;
    private DepLoan depLoan2;

    public Account() {
    }

    public Account(String fullName, String username, String password, String phoneNumber) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.amount=0;
    }

    public Account(AccountBuilder accountBuilder) {
        this.fullName = accountBuilder.getAccount().getFullName();
        this.username = accountBuilder.getAccount().getUsername();
        this.password = accountBuilder.getAccount().getPassword();
        this.phoneNumber = accountBuilder.getAccount().getPhoneNumber();
        this.depLoan = accountBuilder.getAccount().getDepLoan();
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public DepLoan getDepLoan() {
        return depLoan;
    }

    public void setDepLoan(DepLoan depLoan) {
        this.depLoan = depLoan;
    }

    @Override
    public String toString() {
        if (depLoan!=null)
        return "Account{" +
                "fullName='" + fullName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", amount=" + amount +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", depLoan=" + depLoan.toString() +
                '}';
        return "Account{" +
                "fullName='" + fullName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", amount=" + amount +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    @Override
    public void update(Publisher publisher) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        if (((NationalBankPublisher) publisher).getlKZRUCurrency() != ((NationalBankPublisher) publisher).getKZRUCurrency()) {
            System.out.println("KZ-RU:" + ((NationalBankPublisher) publisher).getlKZRUCurrency() + "->" + ((NationalBankPublisher) publisher).getKZRUCurrency()+" "+formatter.format(date));
            System.out.println("Currency changed!");
        }
        if (((NationalBankPublisher) publisher).getlKZUSCurrency() != ((NationalBankPublisher) publisher).getKZUSCurrency()){
            System.out.println("KZ-US:" + ((NationalBankPublisher) publisher).getlKZUSCurrency() + "->" + ((NationalBankPublisher) publisher).getKZUSCurrency()+" "+formatter.format(date));
            System.out.println("Currency changed!");
        }
        if(((NationalBankPublisher) publisher).getlRUUSDCurrency()!=((NationalBankPublisher) publisher).getRUUSDCurrency()){
            System.out.println("RU-US:"+((NationalBankPublisher) publisher).getlRUUSDCurrency()+"->"+((NationalBankPublisher) publisher).getRUUSDCurrency()+" "+formatter.format(date));
            System.out.println("Currency changed!");
        }
        System.out.println();
    }

    public DepLoan getDepLoan2() {
        return depLoan2;
    }

    public void setDepLoan2(DepLoan depLoan2) {
        this.depLoan2 = depLoan2;
    }
}
