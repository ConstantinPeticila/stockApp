package com.investing.service;

import com.investing.domain.*;
import com.investing.exceptions.StockExistsException;
import com.investing.exceptions.UserExistsException;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class InvestingService {

    public static void addUser(InvestingApp investingApp, User user) throws UserExistsException {
        investingApp.addUser(user);
    }

    public static void addStock(InvestingApp investingApp, Stock stock) throws StockExistsException {
        investingApp.addStock(stock);
    }

    public static Set<Stock> viewStocks(InvestingApp investingApp){
        return investingApp.getStocks();
    }

    public static void printMaximumAmountToWithdraw(InvestingApp investingApp) {

        System.out.format("%nPrint maximum amount to withdraw for all clients%n");

        StringBuilder result = new StringBuilder();
        for (User user : investingApp.getUsers()) {
            result.append("User: ")
                    .append(user)
                    .append("\n")
                    .append("Maximum amount to withdraw: ")
                    .append(Math.round(user.getAccount().maximumAmountToWithdraw() * 100) / 100d)
                    .append("\n");
        }

        System.out.println(result.toString());
    }

    public static void printStatistics(InvestingApp investingApp) {
        System.out.println("\n------------------------- You started Bank Application with STATISTICS ------------------------------\n");

        int nrOfClients = AppReport.getNumberOfUsers(investingApp);
        System.out.println("Total number of clients is: " + nrOfClients + "\n");

        int nrOfStocks = AppReport.getNumberOfStocks(investingApp);
        System.out.println("Total number of stocks is: " + nrOfStocks + "\n");

        int nrOfAccounts = AppReport.getNumberOfTrades(investingApp);
        System.out.println("Total number of trades is: " + nrOfAccounts + "\n");

        Set<User> usersSorted = AppReport.getUsersSorted(investingApp);
        System.out.println("All users in alphabetically order:");
        usersSorted.forEach(System.out::println);
        System.out.println();

        double totalSum = AppReport.getTotalSumOfAccounts(investingApp);
        System.out.println("Total sum from the accounts of all app users is: " + totalSum + "\n");

        double totalSumInvestedInStocks = AppReport.getTotalSumInvestedInStocks(investingApp);
        System.out.println("Total sum from the accounts of all app user invested in stocks is: " + totalSumInvestedInStocks + "\n");

        System.out.println("All users ordered by invested sum in stocks:");
        Set<User> usersSortedBySum = AppReport.getUsersSortedByInvestedSum(investingApp);
        usersSortedBySum.forEach(user -> System.out.println(user.toString()));
        System.out.println();

        System.out.println("All users ordered by the number of trades that they made:");
        Set<User> usersSortedByTrades = AppReport.getUsersSortedByNumberOfTrades(investingApp);
        usersSortedByTrades.forEach(user -> System.out.println(user.toString()));
        System.out.println();


        System.out.println("All stocks ordered by the number of sold actions:");
        Set<Stock> stockBySoldActions = AppReport.getStockBySoldActions(investingApp);
        stockBySoldActions.forEach(stock -> System.out.println(stock.toString()));
        System.out.println();


        System.out.println("Display all trades for each client");
        Map<User, Map<Stock, Double>> usersTrades = AppReport.getUsersTrades(investingApp);
        usersTrades.forEach((user, trades) -> {
            System.out.println(user);
            trades.forEach((stock, amount) -> System.out.println(stock + " ---- " + amount));
        });
        System.out.println();

//        System.out.println("List of all clients grouped by city and ordered alphabetically by name:");
//        Map<String, List<User>> clientsByCity = AppReport.getUsersByCity(investingApp);
//        clientsByCity.forEach((city, listOfClients) -> System.out.println("From " + city + " are next clients: " + listOfClients));
        System.out.println("\n------------------------------------ End of the  STATISTICS ------------------------------------\n");
    }


}
