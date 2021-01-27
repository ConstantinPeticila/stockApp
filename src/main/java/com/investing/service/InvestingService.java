package com.investing.service;

import com.investing.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;

public class InvestingService {

    static Logger logger = LoggerFactory.getLogger(InvestingService.class);

    public static void printStatistics(InvestingApp investingApp) {
       logger.info("------------------------- You started Bank Application with STATISTICS ------------------------------\n");

        int nrOfClients = AppReport.getNumberOfUsers(investingApp);
        logger.info("Total number of clients is: " + nrOfClients + "\n");

        int nrOfStocks = AppReport.getNumberOfStocks(investingApp);
        logger.info("Total number of stocks is: " + nrOfStocks + "\n");

        int nrOfTrades = AppReport.getNumberOfTrades(investingApp);
        logger.info("Total number of trades is: " + nrOfTrades + "\n");

        Set<User> usersSorted = AppReport.getUsersSorted(investingApp);
        logger.info("All users in alphabetically order:\n");
        usersSorted.forEach(user -> logger.info(user.toString()));

        double totalSum = AppReport.getTotalSumOfAccounts(investingApp);
        logger.info("Total sum from the users accounts is: " + totalSum + "\n");

        double totalSumInvestedInStocks = AppReport.getTotalSumInvestedInStocks(investingApp);
        logger.info("Total sum from the users accounts invested in stocks is: " + totalSumInvestedInStocks + "\n");

        logger.info("All users ordered by invested sum in stocks:\n");
        Set<User> usersSortedBySum = AppReport.getUsersSortedByInvestedSum(investingApp);
        usersSortedBySum.forEach(user -> logger.info(user.toString()));

        logger.info("All users ordered by their balance:\n");
        Set<User> usersSortedByBalance = AppReport.getUsersSortedByBalance(investingApp);
        usersSortedByBalance.forEach(user -> logger.info(user.toString()));

        logger.info("All users ordered by the number of trades that they made: \n");
        Set<User> usersSortedByTrades = AppReport.getUsersSortedByNumberOfTrades(investingApp);
        usersSortedByTrades.forEach(user -> logger.info(user.toString()));

        logger.info("All stocks ordered by the number of sold actions: \n");
        Set<Stock> stockBySoldActions = AppReport.getStockBySoldActions(investingApp);
        stockBySoldActions.forEach(stock -> logger.info(stock.toString()));

        logger.info("All increasing stocks: \n");
        Set<Stock> increasingStocks = AppReport.getIncreasingStocks(investingApp);
        increasingStocks.forEach(stock -> logger.info(stock.toString()));

        logger.info("Display all trades for each client\n");
        Map<User, Map<Stock, Double>> usersTrades = AppReport.getUsersTrades(investingApp);
        usersTrades.forEach((user, trades) -> {
            logger.info(user.toString());
            trades.forEach((stock, amount) -> logger.info(stock + " ---- " + amount ));
        });
        logger.info("------------------------------------ End of the  STATISTICS ------------------------------------\n");
    }


}
