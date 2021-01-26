package com.investing.domain;


import java.util.HashSet;
import java.util.Set;

public class Stock {

    public Set<User> getObservers() {
        return observers;
    }

    private Set<User> observers = new HashSet<>();

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
        setLastPrice(this.price);
        this.price = price;
    }

    public void attach(User user){
        observers.add(user);
    }

    public Double getLastPrice() {
        return lastPrice;
    }

    private void setLastPrice(Double lastPrice) {
        this.lastPrice = lastPrice;
    }
}
