package com.investing.tests;

public class Exercise14Test {
	
//	@Test
//	public void testSavingAccount() throws NotEnoughFundsException {
//		SavingAccount savingAccount = new SavingAccount(1, 1000.0);
//		savingAccount.deposit(100.0);
//		savingAccount.withdraw(50.0);
//		assertEquals(1, savingAccount.getId());
//		assertEquals(1050, savingAccount.getBalance(), 0);
//		assertEquals(1050, savingAccount.maximumAmountToWithdraw(), 0);
//	}
//
//	@Test
//	public void testCheckingAccount() throws OverdraftLimitExceededException {
//		CheckingAccount checkingAccount = new CheckingAccount(2, 1000.0, 100.0);
//		checkingAccount.deposit(100.0);
//		checkingAccount.withdraw(1150.0);
//		assertEquals(2, checkingAccount.getId());
//		assertEquals(-50, checkingAccount.getBalance(), 0);
//		assertEquals(100, checkingAccount.getOverdraft(), 0);
//		assertEquals(50, checkingAccount.maximumAmountToWithdraw(), 0);
//	}
//
//	@Test
//	public void testClient() {
//		User user = new User("Smith John", Gender.MALE, "Bucuresti");
//		user.addAccount(new SavingAccount(1, 1000.0));
//		user.addAccount(new CheckingAccount(2, 1000.0, 100.0));
//		assertEquals(2, user.getAccounts().size());
//		assertEquals("Mr. Smith John", user.getClientGreeting());
//		assertEquals("Bucuresti", user.getCity());
//	}
//
//	@Test
//	public void testBank() throws UserExistsException {
//		EmailService emailService = new EmailService();
//		InvestingApp investingApp = new InvestingApp(emailService);
//		User user1 = new User("Smith John", Gender.MALE, "Bucuresti");
//		user1.addAccount(new SavingAccount(1, 1000.0));
//		user1.addAccount(new CheckingAccount(2, 1000.0, 100.0));
//
//		User user2 = new User("Smith Michelle", Gender.FEMALE, "Bucuresti");
//		user2.addAccount(new SavingAccount(3, 2000.0));
//		user2.addAccount(new CheckingAccount(4, 1500.0, 200.0));
//
//		InvestingService.addUser(investingApp, user1);
//		InvestingService.addUser(investingApp, user2);
//
//		assertEquals(2, investingApp.getUsers().size());
//		emailService.close();
//	}

}
