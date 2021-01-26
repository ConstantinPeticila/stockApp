package com.investing.utils;

import com.investing.domain.Stock;
import com.investing.domain.User;

import java.util.Set;


public interface StockListener {

    void onStockUpdate(Stock stock, Set<User> users);
}
