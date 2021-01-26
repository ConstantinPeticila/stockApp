package com.investing.domain;

import java.util.ArrayList;
import java.util.List;

public class Stock {

    private List<User> observers = new ArrayList<User>();

    public Stock(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", soldActions=" + soldActions +
                '}';
    }

    private String name;
    private Double price;
    private Double soldActions;
    private Double lastPrice;

    public Double getSoldActions() {
        return soldActions;
    }

    public void setSoldActions(Double soldActions) {
        setLastPrice(this.soldActions);
        this.soldActions = soldActions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
//        notifyAllUsers();
    }

    public void attach(User user){
        observers.add(user);
    }

   /* private void notifyAllUsers() {

        for (User user: observers) {
            //user.update();
        }
    }*/

    public Double getLastPrice() {
        return lastPrice;
    }

    private void setLastPrice(Double lastPrice) {
        this.lastPrice = lastPrice;
    }
}
