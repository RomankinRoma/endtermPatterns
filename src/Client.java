import creational.AccountFactory;
import model.Account;

import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        AccountFactory accountFactory = new AccountFactory();


        Account account = accountFactory.createSimpleAccount("Rakhmatulla Amanov", "romankin", "123456789", "87751419810");
//        System.out.println(account.toString());
        Account account2 = accountFactory.createDepositAccount("Rakhmatulla Amanov", "romankin1", "123456789", "87751419810");
//        System.out.println(account2.toString());
        Account account3 = accountFactory.createLoanAccount("Rakhmatulla Amanov", "romankin2", "123456789", "87751419810", 5000);
//        System.out.println(account3.toString());

        ClientFacade clientFacade = new ClientFacade();
        clientFacade.getUsers().add(clientFacade.bank);
        clientFacade.getNationalBankPublisher().addSubscriber(account);
        clientFacade.getNationalBankPublisher().addSubscriber(account2);
        clientFacade.getNationalBankPublisher().addSubscriber(account3);
        clientFacade.getUsers().add(account);
        clientFacade.getUsers().add(account2);
        clientFacade.getUsers().add(account3);

        clientFacade.Menu();

    }
}
