package com.investing.utils;

import com.investing.domain.Stock;
import com.investing.domain.User;

public interface StockListener {

    void onStockUpdate(Stock stock, User user);
}
