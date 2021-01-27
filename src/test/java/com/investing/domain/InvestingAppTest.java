package com.investing.domain;

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
import static org.junit.jupiter.api.Assertions.*;

public class InvestingAppTest {
    public User mihai;
    public Stock stock;
    public InvestingApp investingApp;

    @BeforeEach
    public void initialize() {
        mihai = new User("Mihai", MALE);
        mihai.getAccount().deposit(300);
        stock = new Stock("NIO", 35.5);
        investingApp = new InvestingApp();

    }

    @Test
    public void addUserTest() throws UserExistsException {
        investingApp.addUser(mihai);
        assertTrue(investingApp.getUsers().contains(mihai));
        assertThrows(UserExistsException.class, () -> {
            investingApp.addUser(mihai);
        });
    }

    @Test
    public void getUserTest() throws UserExistsException {
        investingApp.addUser(mihai);
        assertEquals(300, investingApp.getUser("Mihai").getAccount().getBalance());
        assertNull(investingApp.getUser("ionel"));
    }

    @Test
    public void stockTest() throws StockExistsException {
        investingApp.addStock(stock);
        stock.setPrice(71.0);
        investingApp.changeStock(stock);
        assertTrue(investingApp.getStocks().contains(stock));
        assertEquals(71, investingApp.getStocks().iterator().next().getPrice());
        assertThrows(StockExistsException.class, () -> {
            investingApp.addStock(stock);
        });
    }

    @Test
    public void servicesTest() throws StockExistsException, UserExistsException {
        investingApp.setServices(new EmailService(), new NotificationService());
        investingApp.addUser(mihai);
        investingApp.addStock(stock);
        assertTrue(investingApp.getStocks().contains(stock));
    }


}
