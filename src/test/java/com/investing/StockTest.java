package com.investing;

import com.investing.domain.Gender;
import com.investing.domain.Stock;
import com.investing.domain.User;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StockTest {

    @Test
    public void createStock(){
        Stock stock = new Stock("NIO", 34.0);
        assertEquals("NIO", stock.getName());
    }

    @Test
    public void checkToStringStock(){
        Stock stock = new Stock("NIO", 34.0);
        assertTrue(stock.toString().contains("soldActions"));
    }

    @Test
    public void checkPriceStock(){
        Stock stock = new Stock("NIO", 34.0);
        Double first = 4.0;
        Double second = 6.0;
        stock.setPrice(first);
        stock.setPrice(second);
        assertEquals(first, stock.getLastPrice());
        assertEquals(second , stock.getPrice());

    }
    @Test
    public void attachStock(){
        Stock stock = new Stock("NIO", 34.0);
        User mihai = new User("Mihal", Gender.MALE);
       stock.attach(mihai);
        assertTrue(stock.getObservers().contains(mihai));

    }
}
