package com.investing.service;

import com.investing.domain.InvestingApp;
import com.investing.domain.Stock;
import com.investing.domain.User;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AppReport {

    private AppReport() {
    }

    public static int getNumberOfUsers(InvestingApp investingApp) {
        return investingApp.getUsers().size();
    }

    public static int getNumberOfStocks(InvestingApp investingApp) {
        return investingApp.getStocks().size();
    }

    public static int getNumberOfTrades(InvestingApp investingApp) {
        return investingApp.getUsers().stream().
                mapToInt(client -> client.getTrades().size())
                .sum();
    }

    public static Set<User> getUsersSorted(InvestingApp investingApp) {
        TreeSet<User> result = new TreeSet<>(Comparator.comparing(User::getName));
        result.addAll(investingApp.getUsers());
        return result;
    }

    public static Set<User> getUsersSortedByNumberOfTrades(InvestingApp investingApp) {
        Comparator<User> sumComparator = (user1, user2) -> {
            if (user1.getTrades().size() != user2.getTrades().size()) {
                return -(user1.getTrades().size() - user2.getTrades().size());
            } else {
                return -1;
            }
        };
        TreeSet<User> result = new TreeSet<>(sumComparator);
        result.addAll(investingApp.getUsers());
        return result;
    }

    public static Set<User> getUsersSortedByInvestedSum(InvestingApp investingApp) {
        Comparator<User> sumComparator = (user1, user2) -> {
            if (!user1.getStocksTotalMoneySum().equals(user2.getStocksTotalMoneySum())) {
                return (int) -(user1.getStocksTotalMoneySum() - user2.getStocksTotalMoneySum());
            } else {
                return -1;
            }
        };
        TreeSet<User> result = new TreeSet<>(sumComparator);
        result.addAll(investingApp.getUsers());
        return result;
    }

    public static Set<User> getUsersSortedByBalance(InvestingApp investingApp) {
        Comparator<User> sumComparator = (user1, user2) -> {
            if (user1.getAccount().getBalance() != user2.getAccount().getBalance()) {
                return (int) -(user1.getAccount().getBalance() - user2.getAccount().getBalance());
            } else {
                return -1;
            }
        };
        TreeSet<User> result = new TreeSet<>(sumComparator);
        result.addAll(investingApp.getUsers());
        return result;
    }

    public static double getTotalSumInvestedInStocks(InvestingApp investingApp) {
        return investingApp.getUsers().stream()
                .mapToDouble(User::getStocksTotalMoneySum)
                .sum();
    }

    public static double getTotalSumOfAccounts(InvestingApp investingApp) {
        return investingApp.getUsers().stream()
                .mapToDouble(user -> user.getAccount().getBalance())
                .sum();
    }

    public static Set<Stock> getStockBySoldActions(InvestingApp investingApp) {
        Comparator<Stock> sumComparator = (stock1, stock2) -> {
            if (!stock1.getSoldActions().equals(stock2.getSoldActions())) {
                return (int) (stock1.getSoldActions() - stock2.getSoldActions());
            } else {
                return (int) (stock1.getPrice() - stock2.getPrice());
            }
        };
        TreeSet<Stock> result = new TreeSet<>(sumComparator);
        result.addAll(investingApp.getStocks());
        return result;
    }

    public static Set<Stock> getIncreasingStocks(InvestingApp investingApp) {
        return investingApp.getStocks().stream().filter(stock -> stock.getPrice() - stock.getLastPrice() > 0).collect(Collectors.toSet());
    }

    public static Map<User, Map<Stock, Double>> getUsersTrades(InvestingApp investingApp) {
        return investingApp.getUsers().stream()
                .collect(Collectors.toMap(Function.identity(), User::getTrades));
    }
}
