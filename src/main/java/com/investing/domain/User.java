package com.investing.domain;

import com.investing.exceptions.NotEnoughFundsException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class User {
	private String name;
	private Gender gender;
	private String city;
	private UserAccount account;
	//map cu stockurile si numarul de actiuni pentru fiecare
	private Map<Stock, Double> trades= new HashMap<>();


	public User(String name, Gender gender) {
		this.name = name;
		this.gender = gender;
	}

	public User(String name, Gender gender, String city) {
		this.name = name;
		this.gender = gender;
		this.city = city;
		this.account = new UserAccount();
	}

	public void invest(Stock stock, Double amount) throws NotEnoughFundsException {

		if (amount < 0) {
			throw new IllegalArgumentException("Cannot invest a negative amount");
		}
		 //daca are suficienti bani
		if (amount > this.account.maximumAmountToWithdraw()) {
			throw new NotEnoughFundsException(this.account.getBalance(), amount, "Requested amount exceeds the maximum amount available");
		}
		//daca deja a mai investit in actiunea respectiva adaug la numarul de actiuni
		if(this.trades.containsKey(stock))
			this.trades.replace(stock,this.trades.get(stock) + updateStock(stock, amount));

		//daca e prima oara adaug la tradeuri numarul de actiuni luate cu banii respectivi
		else {
			this.trades.putIfAbsent(stock, updateStock(stock, amount));
			stock.attach(this);
		}
		this.account.stockInvest(amount);
	}

//	public void addInterest(Stock stock){
//		stock.attach(this);
//	}

	public Double check(Stock stock) throws NotEnoughFundsException {

		if(trades.get(stock).isNaN()){
			return (double) 0;
		}
		return trades.get(stock) * stock.getPrice();
	}

	public void sell(Stock stock, Double amount) throws NotEnoughFundsException {

		if (amount < 0) {
			throw new IllegalArgumentException("Cannot sell a negative amount");
		}
		if (amount > check(stock)) {
			throw new NotEnoughFundsException(this.account.getBalance(), amount, "Requested amount exceeds the maximum amount available");
		}
		this.account.stockSell(amount);

		if(amount.equals(check(stock))){
			this.trades.remove(stock);
		}else{
			this.trades.replace(stock, updateStock(stock, check(stock) - amount));
		}
	}

	private double updateStock(Stock stock, double amount) {
		return (amount) / stock.getPrice();
	}

	public Double getInvestedSum(){
		return this.trades.entrySet().
				stream()
				.mapToDouble(stock -> stock.getKey().getPrice()*stock.getValue())
				.sum();
	}
	public void setCity(String city) {
		this.city = city;
	}

	public UserAccount getAccount() {
		return account;
	}

	public Map<Stock, Double> getTrades() {
		return Collections.unmodifiableMap(trades);
	}

	public String getName() {
		return name;
	}

	public Gender getGender() {
		return gender;
	}

	public String getCity() {
		return city;
	}

	public String getClientGreeting() {
		if (gender != null) {
			return gender.getGreeting() + " " + name;
		} else {
			return name;
		}
	}



//	@Override
//	public String toString() {
//		StringBuffer stringBuffer = new StringBuffer();
//		stringBuffer.append("{");
//		accounts.forEach(account -> stringBuffer.append(account.toString() + ","));
//		stringBuffer.setLength(stringBuffer.length()-1);
//		stringBuffer.append("}");
//		return "Client{" +
//				"name='" + name + '\'' +
//				", gender=" + gender +
//				", accounts=" + stringBuffer +
//				'}';
//	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof User)) return false;
		User user = (User) o;
		return Objects.equals(getName(), user.getName()) &&
				getGender() == user.getGender();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getName(), getGender());
	}

	@Override
	public String toString() {
		return "User{" +
				"name='" + name + '\'' +
				", gender=" + gender +
				", city='" + city + '\'' +
				", account=" + account +
				", trades=" + trades +
				'}';
	}
}
