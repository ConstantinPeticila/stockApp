package com.investing.service;

import com.investing.domain.InvestingApp;
import com.investing.domain.Stock;
import com.investing.domain.User;
import com.investing.email.EmailService;
import com.investing.email.NotificationService;
import com.investing.exceptions.NotEnoughFundsException;
import com.investing.exceptions.StockExistsException;
import com.investing.exceptions.UserExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static com.investing.domain.Gender.MALE;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppReportTest {
    public User mihai, andrei;
    public Stock stock1, stock2;
    public InvestingApp investingApp;

    @BeforeEach
    public void initialize() throws UserExistsException, StockExistsException, NotEnoughFundsException {
        mihai = new User("Mihai", MALE);
        andrei = new User("Andrei", MALE);
        mihai.getAccount().deposit(300);
        stock1 = new Stock("NIO", 35.5);
        stock2 = new Stock("IO", 31.5);
        mihai.invest(stock1, 200.0);

        investingApp = new InvestingApp();
        investingApp.addUser(mihai);
        investingApp.addUser(andrei);
        investingApp.addStock(stock1);
        investingApp.addStock(stock2);

    }

    @Test
    public void numberOfStocksTest() {
        int numberOfStocks = AppReport.getNumberOfStocks(investingApp);
        assertEquals(2, numberOfStocks);
    }

    @Test
    public void numberOfUsersTest() {
        int numberOfUsers = AppReport.getNumberOfUsers(investingApp);
        assertEquals(2, numberOfUsers);
    }

    @Test
    public void usersSortedTest() {
        assertEquals(andrei, AppReport.getUsersSorted(investingApp).iterator().next());
    }

    @Test
    public void usersSortedByInvestedSumTest() {
        assertEquals(mihai, AppReport.getUsersSortedByInvestedSum(investingApp).iterator().next());
    }

    @Test
    public void usersSortedByBalanceTest() {
        assertEquals(mihai, AppReport.getUsersSortedByBalance(investingApp).iterator().next());
    }

    @Test
    public void increasingStocksTest() {
        stock1.setPrice(300.0);
        assertEquals(Set.of(stock1), AppReport.getIncreasingStocks(investingApp));
    }
}
