package com.investing.main;

import com.investing.domain.*;
import com.investing.email.NotificationService;
import com.investing.exceptions.EmailException;
import com.investing.exceptions.NotEnoughFundsException;
import com.investing.exceptions.StockExistsException;
import com.investing.exceptions.UserExistsException;
import com.investing.email.EmailService;
import com.investing.service.InvestingService;

public class MainInvestingApp {

    private static final String STATISTICS = "statistics";
    private static InvestingApp investingApp;
    private static final EmailService emailService = new EmailService();
    private static final NotificationService notificationService = new NotificationService();

    public static void main(String[] args) throws UserExistsException, StockExistsException, NotEnoughFundsException, EmailException {
        investingApp = new InvestingApp();
        investingApp.setServices(emailService, notificationService);

        modifyApp();
        printBalance();

        if (args[0].equalsIgnoreCase(STATISTICS)) {
            addUserAndStcok(investingApp);
            InvestingService.printStatistics(investingApp);
        }
        emailService.close();
        notificationService.close();


    }


    private static void modifyApp() throws UserExistsException, StockExistsException, NotEnoughFundsException {
        User user1 = new User("Cosmin", Gender.MALE);
        User user2 = new User("Bogdan", Gender.MALE);
        user2.getAccount().deposit(200);
        user1.getAccount().deposit(300);
        Stock stock1 = new Stock("Nio", 20.0);
        Stock stock2 = new Stock("Tesla", 50.0);

        investingApp.addUser(user1);
        investingApp.addUser(user2);
        investingApp.addStock(stock1);
        investingApp.addStock(stock2);
        user1.invest(stock1, 100.0);
        stock1.setPrice(40.0);
        investingApp.changeStock(stock1);

    }

    private static void printBalance() {
        System.out.format("%nPrint balance for all users%n");
        for (User user : investingApp.getUsers()) {
            System.out.println("User: " + user + "Account:  %.2f%n" + user.getAccount().getBalance());
        }

        System.out.format("%nPrint price for all stocks%n");
        for (Stock stock : investingApp.getStocks()) {
            System.out.println("stock: " + stock.getName() + " with price:  %.2f" + stock.getPrice() + " has %.2f sold actions %n" + stock.getSoldActions());
        }
    }

    private static void addUserAndStcok(InvestingApp investingApp) throws NotEnoughFundsException, UserExistsException, StockExistsException {
        User user = new User("Alex", Gender.MALE);
        Stock stock = new Stock("Apple", 20.0);
        user.getAccount().deposit(300);

        user.invest(stock, 200.0);
        investingApp.addUser(user);
        investingApp.addStock(stock);

    }

}
