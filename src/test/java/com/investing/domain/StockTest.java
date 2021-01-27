package com.investing.domain;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StockTest {

    @Test
    public void checkToStringStock() {
        Stock stock = new Stock("NIO", 34.0);
        assertTrue(stock.toString().contains("soldActions"));
    }

    @Test
    public void attachStock() {
        Stock stock = new Stock("NIO", 34.0);
        User mihai = new User("Mihal", Gender.MALE);
        stock.attach(mihai);
        assertTrue(stock.getObservers().contains(mihai));

    }

    @Test
    public void membersStock() {
        Stock stock = new Stock("NIO", 34.0);
        Double price = 3.9;
        Double updatedPrice = 6.0;
        Double soldActions = 30.0;

        stock.setPrice(price);
        stock.setPrice(updatedPrice);
        stock.setName("IIO");
        stock.setSoldActions(soldActions);

        assertEquals(soldActions, stock.getSoldActions());
        assertEquals("IIO", stock.getName());
        assertEquals(updatedPrice, stock.getPrice());
        assertEquals(price, stock.getLastPrice());
    }
}
