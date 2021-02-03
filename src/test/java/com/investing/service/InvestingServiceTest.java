package com.investing.service;

import com.investing.domain.InvestingApp;
import com.investing.domain.Stock;
import com.investing.domain.User;
import com.investing.email.EmailService;
import com.investing.email.NotificationService;
import com.investing.exceptions.StockExistsException;
import com.investing.exceptions.UserExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.investing.domain.Gender.MALE;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InvestingServiceTest {

    public User mihai;
    public Stock stock;

    @BeforeEach
    public void initialize() {
        mihai = new User("Mihai", MALE);
        mihai.getAccount().deposit(300);
        stock = new Stock("NIO", 35.5);
    }

    @Test
    public void statisticsTests() throws UserExistsException, StockExistsException {
        InvestingApp investingApp = new InvestingApp();
        investingApp.setServices(new EmailService(), new NotificationService());
        investingApp.addUser(mihai);
        investingApp.addStock(stock);
        investingApp.addStock(new Stock("IO", 32.2));
        InvestingService.printStatistics(investingApp);

        assertEquals(mihai, AppReport.getUsersSortedByInvestedSum(investingApp).iterator().next());

    }

}
