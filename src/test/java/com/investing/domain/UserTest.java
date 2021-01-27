package com.investing.domain;


import com.investing.domain.Stock;
import com.investing.domain.User;
import com.investing.exceptions.NotEnoughFundsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static com.investing.domain.Gender.MALE;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    public User mihai;
    public Stock stock;

    @BeforeEach
    public void initialize() {
        mihai = new User("Mihai", MALE);
        mihai.getAccount().deposit(300);
        stock = new Stock("NIO", 35.5);
    }

    @Test
    public void investTest() throws NotEnoughFundsException {
        mihai.invest(stock, 20.0);
        mihai.invest(stock, 40.0);
        assertTrue(mihai.getTrades().containsKey(stock));
        assertThrows(NotEnoughFundsException.class, () -> {
            mihai.invest(stock, 500.0);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            mihai.invest(stock, -500.0);
        });
    }

    @Test
    public void interestTest() {
        mihai.addInterest(stock);
        assertTrue(stock.getObservers().contains(mihai));
    }

    @Test
    public void checkStockTest() throws NotEnoughFundsException {
        assertEquals(0, mihai.check(stock));
        mihai.invest(stock, 20.0);
        assertEquals(20, mihai.check(stock));

    }

    @Test
    public void sellTest() throws NotEnoughFundsException {
        mihai.invest(stock, 20.0);
        mihai.sell(stock, 10.0);
        assertTrue(mihai.getTrades().containsKey(stock));
        mihai.sell(stock, 10.0);
        assertTrue(!mihai.getTrades().containsKey(stock));
        assertThrows(NotEnoughFundsException.class, () -> {
            mihai.sell(stock, 500.0);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            mihai.sell(stock, -500.0);
        });

    }

    @Test
    public void getStockMoneyTest() throws NotEnoughFundsException {
        mihai.invest(stock, 20.0);
        stock.setPrice(71.0);
        assertEquals(40, mihai.getStocksTotalMoneySum());
    }

    @Test
    public void greetingTest() {
        assertTrue(mihai.getClientGreeting().contains("Mr"));
    }

    @Test
    public void toStringTest() {
        assertEquals("Mihai", mihai.getName());
        assertEquals(MALE, mihai.getGender());
        assertTrue(mihai.toString().contains("trades"));
        assertTrue(mihai.toString().contains("gender"));
    }

    @Test
    public void depositTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            mihai.getAccount().deposit(-10.0);
        });
    }


}
