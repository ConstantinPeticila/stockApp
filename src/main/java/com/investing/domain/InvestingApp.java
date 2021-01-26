package com.investing.domain;

import com.investing.email.*;
import com.investing.exceptions.StockExistsException;
import com.investing.exceptions.UserExistsException;
import com.investing.utils.StockListener;
import com.investing.utils.UserRegistrationListener;

import java.util.*;
import java.util.logging.Logger;

public class InvestingApp {

	private static final Logger LOGGER = Logger.getLogger(InvestingApp.class.getName());

	private final Set<User> users = new HashSet<>();
	private final Set<Stock> stocks = new HashSet<>();

	private final UserRegistrationListener emailListener;
	private final Map<String,StockListener> listeners2 = new HashMap<>();


	private EmailService emailService;
	private NotificationService notificationService;

	private User system = new User("System", Gender.MALE);

	public InvestingApp() {

		emailListener = new EmailNotificationListener();
		listeners2.put("1", new StockAddedNotificationListener());
		listeners2.put("2", new StockModifiedNotificationListener());
	}

	public void setServices(EmailService emailService, NotificationService notificationService) {
		this.emailService =  emailService;
		this.notificationService = notificationService;
	}

	public void addUser(final User user) throws UserExistsException {
    	if (users.contains(user)) {
    		throw new UserExistsException("User already exists into the app");
    	}

    	users.add(user);
        notify(user);
	}

	public void addStock(Stock stock) throws StockExistsException {

		if (stocks.contains(stock)) {
			throw new StockExistsException("Stock already exists into the app");
		}

		stocks.add(stock);
		notify(stock,users, "1");
	}

	public void changeStock(Stock stock) throws StockExistsException {
		stocks.add(stock);
		notify(stock,stock.getObservers(),"2");
	}

	private void notify(User user) {
      emailListener.onUserAdded(user);
    }

	private void notify(Stock stock,Set<User> users, String key) {
		listeners2.get(key).onStockUpdate(stock, users);
	}
	
	public Set<User> getUsers() {
		return Collections.unmodifiableSet(users);
	}

	public User getUser(String name) {
		for( User user: users)
			if(user.getName().equalsIgnoreCase(name))
				return user;
		return null;
	}

	public Set<Stock> getStocks() {
		return Collections.unmodifiableSet(stocks);
	}


	class StockModifiedNotificationListener implements StockListener{

		@Override
		public void onStockUpdate(Stock stock, Set<User> users) {
			System.out.println("The stock " + stock.getName() + " will be changed");
			if (notificationService != null) {
				try {
					notificationService.sendNotificationEmail(
							new Notification()
									.setFrom(stock)
									.setTo(users)
									.setTitle("Stock changed")
									.setBody("Stock" + stock.getName() + "price has been update from " + stock.getLastPrice() + " to value " + stock.getPrice())
					);
				} catch (EmailException e) {
					System.err.println(e.getMessage());
				}
			}
		}
	}

	class StockAddedNotificationListener implements StockListener {

		@Override
		public void onStockUpdate(Stock stock, Set<User> users) {
			System.out.println("The stock " + stock.getName() + " will be added");
			if (notificationService != null) {
				try {
					notificationService.sendNotificationEmail(
							new Notification()
									.setFrom(stock)
									.setTo(users)
									.setTitle("Stock added")
									.setBody("Stock " + stock.getName() + " has been added with value " + stock.getPrice())
					);
				} catch (EmailException e) {
					System.err.println(e.getMessage());
				}
				System.out.println("succes");
			}
		}
	}

	class EmailNotificationListener implements UserRegistrationListener{

		@Override
		public void onUserAdded(User user) {
			System.out.println("Notification email for user " + user.getName() + " to be sent");

			if (emailService != null) {
				try {
					emailService.sendNotificationEmail(
							new Email()
									.setFrom(system)
									.setTo(user)
									.setTitle(" user added notification")
									.setBody("User added " + user)
					);
				} catch (EmailException e) {
					System.err.println(e.getMessage());
				}

			}
		}
	}

}




