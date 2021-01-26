package com.investing.main;

import com.investing.domain.*;
import com.investing.email.NotificationService;
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

    public static void main(String[] args) {
        investingApp = new InvestingApp();
        investingApp.setServices(emailService, notificationService);

        modifyApp();
        //printBalance();
        //InvestingService.printMaximumAmountToWithdraw(investingApp);

//        if (args[0].equalsIgnoreCase(STATISTICS)) {
//            addUser(investingApp);
//            InvestingService.printStatistics(investingApp);
//        }

        emailService.close();
        notificationService.close();
        //emailService.sendNotificationEmail(null);
    }


    private static void modifyApp() {
        User user1 = new User("John", Gender.MALE);
//        User user2 = new User("Alex", Gender.MALE, "Bucuresti");
        user1.getAccount().deposit(300);
        Stock stock1 = new Stock("Nio",34.0);
        try {
           investingApp.addUser(user1);
//            InvestingService.addUser(investingApp, user2);
        } catch (UserExistsException e) {
            System.out.format("Cannot add an already existing client: %s%n", user1.getName());
        }
        
        try {
            InvestingService.addStock(investingApp, stock1);
        } catch (StockExistsException e){
            System.out.format("Cannot add an already existing stock: %s%n", stock1.getName());
        }

//        try {
//            user1.getAccount().withdraw(10);
//        } catch (NotEnoughFundsException e) {
//            System.out.format("Not enough funds for user account with balance: %.2f, tried to extract amount: %.2f%n", e.getBalance(), e.getAmount());
//        }
//
//        try {
//            user1.invest(stock1, 200.0);
//        } catch (NotEnoughFundsException e) {
//            System.out.println("-----------------> Not enough money ");
//        }

//        try {
//            InvestingService.addUser(investingApp, user1);
//        } catch (UserExistsException e) {
//            System.out.format("Cannot add an already existing client: %s%n", user1);
//        }
    }

    private static void printBalance() {
        System.out.format("%nPrint balance for all users%n");
        for (User user : investingApp.getUsers()) {
            System.out.println("User: " + user + "Account:  %.2f%n" + user.getAccount().getBalance());
        }

        System.out.format("%nPrint balance for all stocks%n");
        for (Stock stock : investingApp.getStocks()) {
            System.out.println("stock: " + stock.getName() + " with price:  %.2f" + stock.getPrice() + " has %.2f sold actions %n" + stock.getSoldActions());
        }
    }

    private static void addUser(InvestingApp investingApp) {
        User user = new User("Alex", Gender.MALE);
        Stock stock= new Stock("Tesla",20.0);
        user.getAccount().deposit(300);

        try {
            user.invest(stock, 200.0);
        } catch (NotEnoughFundsException e) {
            System.out.println("-----------------> Not enough money ");
        }
        try {
            investingApp.addUser(user);
        } catch (UserExistsException e) {
            System.out.println("-------------------> The user already exists");
        }
        try {
            investingApp.addStock(stock);
        } catch (StockExistsException e) {
            System.out.println("-------------------> The stock already exists");
        }

    }

}
