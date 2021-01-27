package com.investing.service;

import com.investing.domain.*;

import java.util.Map;
import java.util.Set;

public class InvestingService {

    public static void printStatistics(InvestingApp investingApp) {
        System.out.println("\n------------------------- You started Bank Application with STATISTICS ------------------------------\n");

        int nrOfClients = AppReport.getNumberOfUsers(investingApp);
        System.out.println("Total number of clients is: " + nrOfClients + "\n");

        int nrOfStocks = AppReport.getNumberOfStocks(investingApp);
        System.out.println("Total number of stocks is: " + nrOfStocks + "\n");

        int nrOfTrades = AppReport.getNumberOfTrades(investingApp);
        System.out.println("Total number of trades is: " + nrOfTrades + "\n");

        Set<User> usersSorted = AppReport.getUsersSorted(investingApp);
        System.out.println("All users in alphabetically order:");
        usersSorted.forEach(System.out::println);
        System.out.println();

        double totalSum = AppReport.getTotalSumOfAccounts(investingApp);
        System.out.println("Total sum from the users accounts is: " + totalSum + "\n");

        double totalSumInvestedInStocks = AppReport.getTotalSumInvestedInStocks(investingApp);
        System.out.println("Total sum from the users accounts invested in stocks is: " + totalSumInvestedInStocks + "\n");

        System.out.println("All users ordered by invested sum in stocks:");
        Set<User> usersSortedBySum = AppReport.getUsersSortedByInvestedSum(investingApp);
        usersSortedBySum.forEach(user -> System.out.println(user.toString()));
        System.out.println();

        System.out.println("All users ordered by their balance:");
        Set<User> usersSortedByBalance = AppReport.getUsersSortedByBalance(investingApp);
        usersSortedByBalance.forEach(user -> System.out.println(user.toString()));
        System.out.println();

        System.out.println("All users ordered by the number of trades that they made:");
        Set<User> usersSortedByTrades = AppReport.getUsersSortedByNumberOfTrades(investingApp);
        usersSortedByTrades.forEach(user -> System.out.println(user.toString()));
        System.out.println();


        System.out.println("All stocks ordered by the number of sold actions:");
        Set<Stock> stockBySoldActions = AppReport.getStockBySoldActions(investingApp);
        stockBySoldActions.forEach(stock -> System.out.println(stock.toString()));
        System.out.println();

        System.out.println("All increasing stocks:");
        Set<Stock> increasingStocks = AppReport.getIncreasingStocks(investingApp);
        increasingStocks.forEach(stock -> System.out.println(stock.toString()));
        System.out.println();

        System.out.println("Display all trades for each client");
        Map<User, Map<Stock, Double>> usersTrades = AppReport.getUsersTrades(investingApp);
        usersTrades.forEach((user, trades) -> {
            System.out.println(user);
            trades.forEach((stock, amount) -> System.out.println(stock + " ---- " + amount));
        });
        System.out.println();
        System.out.println("\n------------------------------------ End of the  STATISTICS ------------------------------------\n");
    }


}
