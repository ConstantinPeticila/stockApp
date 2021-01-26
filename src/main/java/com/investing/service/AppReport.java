package com.investing.service;

import com.investing.domain.InvestingApp;
import com.investing.domain.Stock;
import com.investing.domain.User;
import com.investing.domain.UserAccount;

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
            if(user1.getTrades().size() != user2.getTrades().size()){
                return (int) (user1.getTrades().size()- user2.getTrades().size());
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
            if(user1.getInvestedSum() != user2.getInvestedSum()){
                return (int) (user1.getInvestedSum() - user2.getInvestedSum());
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
                .mapToDouble(User::getInvestedSum)
                .sum();
    }

    public static double getTotalSumOfAccounts(InvestingApp investingApp) {
        return investingApp.getUsers().stream()
                .mapToDouble(user -> user.getAccount().getBalance())
                .sum();
    }

    public static Set<Stock> getStockBySoldActions(InvestingApp investingApp){
        Comparator<Stock> sumComparator = (stock1, stock2) -> {
            if(stock1.getSoldActions() != stock2.getSoldActions()){
                return (int) (stock1.getSoldActions() -stock2.getSoldActions());
            } else{
                return (int) (stock1.getPrice() - stock2.getPrice());
            }
        };
        TreeSet<Stock> result = new TreeSet<>(sumComparator);
        result.addAll(investingApp.getStocks());
        return result;
    }

    public static Set<UserAccount> getAccountsSortedBySum(InvestingApp investingApp) {
        Comparator<UserAccount> sumComparator = (account1, account2) -> {
            if(account1.getBalance() != account2.getBalance()){
                return (int) (account1.getBalance() - account2.getBalance());
            } else {
                return -1;
            }
        };
        TreeSet<UserAccount> result = new TreeSet<>(sumComparator);
       investingApp.getUsers().forEach(e -> result.add(e.getAccount()));
        return result;
    }

    public static Map<User, Map<Stock, Double>> getUsersTrades(InvestingApp investingApp) {
        return investingApp.getUsers().stream()
                .collect(Collectors.toMap(Function.identity(), User::getTrades));
    }

    public static Map<String, List<User>> getUsersByCity(InvestingApp investingApp) {

        return new TreeMap<>(investingApp.getUsers().stream()
                .collect(Collectors.groupingBy(User::getCity)));
    }
}
