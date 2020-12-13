import behavioral.observer.NationalBankPublisher;
import behavioral.state.StateImpl;
import creational.AccountBuilder;
import creational.AccountFactory;
import creational.Singleton;
import model.Account;
import model.DepLoan;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientFacade {

    List<Account> users = new ArrayList<>();
    NationalBankPublisher nationalBankPublisher = new NationalBankPublisher();
    Singleton singleton = Singleton.getInstance(nationalBankPublisher);
    Scanner in = new Scanner(System.in);
    AccountFactory accountFactory = new AccountFactory();

    Account bank=new AccountBuilder(new Account("Bank","Bank","1qaz2wsx","777777")).createAccount();

    Account currentUser = null;

    public void Menu(){
        sing();
        if (currentUser.getUsername().equals("Bank") &&  currentUser.getPassword().equals("1qaz2wsx")){
            adminMenu();
        }else {
            userMenu();
        }
    }

    public void sing() {
        while (currentUser == null) {
            System.out.println("1.Login");
            System.out.println("2.Register");
            String chose = in.next();
            if ("1".equals(chose))
                login();
            else if (chose.equals("2"))
                register();
            else {
                System.out.println("Incorrect chose");
            }
        }
    }

    public void login() {
        System.out.println("");
        System.out.println("Input username:");
        String username = in.next();
        System.out.println("Input password:");
        String password = in.next();
        users.forEach(account -> {
            if (account.getUsername().equals(username) && account.getPassword().equals(password))
                currentUser = account;
        });
        if (currentUser == null) {
            System.out.println("Incorrect username or password");
        }
    }

    public void adminMenu(){
        if (currentUser.getUsername().equals("Bank")) {
            while (true) {
                System.out.println("1.KZ->US");
                System.out.println("2.RU->US");
                System.out.println("3.KZ->RU");
                System.out.println("0.Log out");
                int chose = in.nextInt();

                switch (chose) {
                    case 1:
                        currency("KZ->US");
                        break;
                    case 2:
                        currency("RU->US");
                        break;
                    case 3:
                        currency("KZ->RU");
                        break;
                    case 0:
                        logout();
                        break;
                    default:
                        System.out.println("Incorrect chose!");
                        break;
                }
            }
    }
    }
    
    public void userMenu(){
        while (true) {
            System.out.println("1.Deposit");
            System.out.println("2.Withdraw");
            System.out.println("3.Take a loan");
            System.out.println("4.Pay loan");
            System.out.println("5.Open a deposit");
            System.out.println("6.Swap to Nat.Bank");
            System.out.println("7.Profile");
            System.out.println("0.Log out");

            int chose = in.nextInt();

            switch (chose) {
                case 1:
                    register();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    takeLoan();
                    break;
                case 4:
                    payLoan();
                    break;
                case 5:
                    openDeposit();
                    break;
                case 6:
                    natBank();
                    break;
                case 7:
                    profile();
                    break;
                case 0:
                    logout();
                    break;
                default:
                    System.out.println("Incorrect chose!");
                    break;
            }
        }
    }
    
    public void currency(String curr){
        switch (curr){
            case "KZ->US":
                System.out.println("Current:"+nationalBankPublisher.getKZUSCurrency());
                System.out.println("Enter new:");
                int newCurr=in.nextInt();
                nationalBankPublisher.setKZUSCurrency(newCurr);
                break;
            case "RU->US":
                System.out.println("Current:"+nationalBankPublisher.getRUUSDCurrency());
                System.out.println("Enter new:");
                newCurr=in.nextInt();
                nationalBankPublisher.setRUUSDCurrency(newCurr);
                break;
            case "KZ->RU":
                System.out.println("Current:"+nationalBankPublisher.getKZUSCurrency());
                System.out.println("Enter new:");
                newCurr=in.nextInt();
                nationalBankPublisher.setKZUSCurrency(newCurr);
                break;
        }
    }

    public void profile() {
        System.out.println("        Profile");
        System.out.println("Amount:" + currentUser.getAmount());
        if (currentUser.getDepLoan() != null && currentUser.getDepLoan().isDeposit())
            System.out.println("Deposit:" + currentUser.getDepLoan().getAmount());
        if (currentUser.getDepLoan() != null && currentUser.getDepLoan().isLoan())
            System.out.println("Loan:" + currentUser.getDepLoan().getAmount());
        if (currentUser.getDepLoan2() != null && currentUser.getDepLoan2().isDeposit())
            System.out.println("Deposit:" + currentUser.getDepLoan2().getAmount());
        if (currentUser.getDepLoan2() != null && currentUser.getDepLoan2().isLoan())
            System.out.println("Loan:" + currentUser.getDepLoan2().getAmount());
        System.out.println("");
    }

    public void register() {
        System.out.println("Input username:");
        String username = in.next();
//        checkUsername(username);
        System.out.println("Input full name:");
        String fullName = in.nextLine();
        fullName = in.nextLine();
        System.out.println("Input phone number:");
        String phoneNumber = in.nextLine();
        System.out.println("Input password:");
        String password = in.nextLine();
        System.out.println("Do you want open deposit or take a loan? y/n");
        String answer = in.nextLine();
        if (answer.toLowerCase().equals("y") || answer.toLowerCase().equals("yes")) {
            System.out.println("1.Deposit");
            System.out.println("2.Loan");
            int chose = in.nextInt();
            if (chose == 1) {
                users.add(accountFactory.createDepositAccount(fullName, username, password, phoneNumber));
                nationalBankPublisher.addSubscriber(users.get(users.size() - 1));
//                System.out.println(users.get(users.size() - 1).toString());
                System.out.println("Successful!");

            } else if (chose == 2) {
                System.out.println("How much money do you want?");
                int amount = in.nextInt();
                users.add(accountFactory.createLoanAccount(fullName, username, password, phoneNumber, amount));
//                System.out.println(users.get(users.size() - 1).toString());
                nationalBankPublisher.addSubscriber(users.get(users.size() - 1));
                System.out.println("Successful!");

            }
        } else if (answer.toLowerCase().equals("n") || answer.toLowerCase().equals("no")) {
            users.add(accountFactory.createSimpleAccount(fullName, username, password, phoneNumber));
//            System.out.println(users.get(users.size() - 1).toString());
            nationalBankPublisher.addSubscriber(users.get(users.size() - 1));
            System.out.println("Successful!");
        }
    }


    public void takeLoan(){
        System.out.println("Input amount:");
        int amount=in.nextInt();
        if (currentUser.getDepLoan()==null){
            currentUser.setDepLoan(new DepLoan(amount,new StateImpl(),false,true));
            System.out.println("Loan approved");
        }else if (currentUser.getDepLoan()!=null && currentUser.getDepLoan().isDeposit()){
            currentUser.setDepLoan2(new DepLoan(amount,new StateImpl(),false,true));
            System.out.println("Loan approved");
        }else {
            System.out.println("You already have a loan");
        }
    }


    public void payLoan(){
        if (currentUser.getDepLoan().isLoan()){
            System.out.println(currentUser.getDepLoan().getAmount());
            System.out.println("Input amount:");
            int amount = in.nextInt();
            currentUser.getDepLoan().setAmount(currentUser.getDepLoan().getAmount()-amount);
            if (currentUser.getDepLoan().getAmount()<=0){
                currentUser.setDepLoan(currentUser.getDepLoan2());
                currentUser.setDepLoan2(null);
                System.out.println("Loan closed");
            }
        }else if (currentUser.getDepLoan2().isLoan()){
            System.out.println(currentUser.getDepLoan().getAmount());
            System.out.println("Input amount:");
            int amount = in.nextInt();
            currentUser.getDepLoan().setAmount(currentUser.getDepLoan().getAmount()-amount);
            if (currentUser.getDepLoan().getAmount()<=0){
                currentUser.setDepLoan2(null);
                System.out.println("Loan closed");
            }
    }}
    public void openDeposit(){
        if (currentUser.getDepLoan()==null){
            currentUser.setDepLoan(new DepLoan(0,new StateImpl(),true,false));
            System.out.println("Deposit opened");

        }else if (currentUser.getDepLoan()!=null && currentUser.getDepLoan().isLoan()){
            currentUser.setDepLoan2(new DepLoan(0,new StateImpl(),true,false));
            System.out.println("Deposit opened");
        }else {
            System.out.println("You have a deposit open");
        }
    }

    public void natBank(){
        System.out.println("Input secret word:");
        String secret=in.nextLine();
        if (secret.equals(bank.getPassword())){
            currentUser=bank;
        }
    }

    public void deposit() {
        System.out.println("Input amount:");
        int amount = in.nextInt();
        System.out.println("1.Master account");
        System.out.println("2.Deposit account");
        int chose = in.nextInt();
        if (chose == 1) {
            currentUser.setAmount(currentUser.getAmount() + amount);
        } else if (chose == 2 && currentUser.getDepLoan().isDeposit()) {
            currentUser.getDepLoan().setAmount(currentUser.getDepLoan().getAmount() + amount);
        }


        System.out.println("Successful!");
    }

    public void checkUsername(String username) {
        for (Account account : users) {
            if (account.getUsername().equals(username)) {
                System.out.println("Username is already in use");
                return;
            }
        }
    }

    public void logout() {
        currentUser = null;
        Menu();
    }

    public AccountFactory getAccountFactory() {
        return accountFactory;
    }

    public void setAccountFactory(AccountFactory accountFactory) {
        this.accountFactory = accountFactory;
    }

    public NationalBankPublisher getNationalBankPublisher() {
        return nationalBankPublisher;
    }

    public void setNationalBankPublisher(NationalBankPublisher nationalBankPublisher) {
        this.nationalBankPublisher = nationalBankPublisher;
    }

    public Singleton getSingleton() {
        return singleton;
    }

    public void setSingleton(Singleton singleton) {
        this.singleton = singleton;
    }

    public List<Account> getUsers() {
        return users;
    }

    public void setUsers(List<Account> users) {
        this.users = users;
    }
}
