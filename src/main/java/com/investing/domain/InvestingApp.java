package com.investing.domain;

import com.investing.email.*;
import com.investing.exceptions.StockExistsException;
import com.investing.exceptions.UserExistsException;
import com.investing.utils.StockListener;
import com.investing.utils.UserRegistrationListener;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.*;
import java.util.logging.Logger;

public class InvestingApp implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(InvestingApp.class.getName());

	private static final long serialVersionUID = -4157871135257285214L;
	private final Set<User> users = new HashSet<>();
	private final Set<Stock> stocks = new HashSet<>();
	private final List<UserRegistrationListener> listeners = new ArrayList<>();
	private final Map<String,StockListener> listeners2 = new HashMap<>();


	private EmailService emailService;
	private NotificationService notificationService;

	private User admin = new User("Admin", Gender.MALE);
	private User system = new User("System", Gender.MALE);
	private int printedUsers = 0;
	private int emailedUsers = 0;
	private int debuggedUsers = 0;

	public InvestingApp() {
//		listeners.add(new PrintUserListener());
		listeners.add(new EmailNotificationListener());
//		listeners.add(new DebugListener());

		listeners2.put("1", new StockAddedNotificationListener());
		listeners2.put("2", new StockModifiedNotificationListener());

		admin.setCity("New York");
		system.setCity("Boston");
	}

	public void setEmailService(EmailService emailService) {
		this.emailService =  emailService;
	}
	public void setNotificationService(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	public int getPrintedUsers() {
		return printedUsers;
	}

	public int getEmailedUsers() {
		return emailedUsers;
	}

	public int getDebuggedUsers() {
		return debuggedUsers;
	}
	
	public void addUser(final User user) throws UserExistsException {
    	if (users.contains(user)) {
    		throw new UserExistsException("User already exists into the app");
    	}
    	LOGGER.info("Adding user and send welcome mail");
    	users.add(user);
        notify(user);
	}

	public void addStock(Stock stock) throws StockExistsException {
		LOGGER.info("Adding stock and notify users");
		if (stocks.contains(stock)) {
			throw new StockExistsException("Stock already exists into the app");
		}

		stocks.add(stock);
//		for(User user: users)
//			notify(stock,user, "1");
	}

	public void changeStock(Stock stock) throws StockExistsException {
		stocks.add(stock);
		for(User user: users)
			if(user.getTrades().containsKey(stock))
			notify(stock,user,"2");
	}

	private void notify(User user) {
        for (UserRegistrationListener listener: listeners) {
            listener.onUserAdded(user);
        }
    }

	private void notify(Stock stock,User user, String key) {
		listeners2.get(key).onStockUpdate(stock, user);
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

	class PrintUserListener implements UserRegistrationListener {
		@Override 
		public void onUserAdded(User user) {
	        System.out.println("User added: " + user.getName());
	        printedUsers++;
	    }

	}

	class StockModifiedNotificationListener implements StockListener{

		@Override
		public void onStockUpdate(Stock stock, User user) {
			System.out.println("The stock " + stock.getName() + " will be changed");
			if (notificationService != null) {
				try {
					notificationService.sendNotificationEmail(
							new Notification()
									.setFrom(stock)
									.setTo(user)
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
		public void onStockUpdate(Stock stock, User user) {
			System.out.println("The stock " + stock.getName() + " will be added");
			if (notificationService != null) {
				try {
					notificationService.sendNotificationEmail(
							new Notification()
									.setFrom(stock)
									.setTo(user)
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

	class EmailNotificationListener implements UserRegistrationListener,Serializable {

		private static final long serialVersionUID = -2360873324733537279L;

		@Override
		public void onUserAdded(User user) {
			System.out.println("Notification email for user " + user.getName() + " to be sent");

			if (emailService != null) {
				try {
					emailService.sendNotificationEmail(
							new Email()
									.setFrom(system)
									.setTo(admin)
									.setCopy(user)
									.setTitle(" user added notification")
									.setBody("User added" + user)
					);
				} catch (EmailException e) {
					System.err.println(e.getMessage());
				}
//	        Email email = new Email(user, "Investing App", user.getName());
//	        emailService.sendNotificationEmail(email);
	        emailedUsers++;
			}
		}
	}
	
	class DebugListener implements UserRegistrationListener {
        @Override 
        public void onUserAdded(User user) {
            System.out.println("User " + user.getName() + " added on: " + DateFormat.getDateInstance(DateFormat.FULL).format(new Date()));
            debuggedUsers++;
        }
    }

}




