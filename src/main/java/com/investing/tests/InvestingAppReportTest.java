package com.investing.tests;

import com.investing.domain.*;
import com.investing.exceptions.UserExistsException;
import com.investing.email.EmailService;
import com.investing.service.AppReport;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;

public class InvestingAppReportTest {

    private static InvestingApp investingApp;
    private static EmailService service = new EmailService();

    @BeforeClass
    public static void generateBank() {
        investingApp = new InvestingApp();
//        for (User user : generateClients()) {
//            try {
//                investingApp.addUser(user);
//            } catch (UserExistsException e) {
//                e.printStackTrace();
//            }
//        }
    }

    @Test
    public void getNumberOfClients() {
        //When
        int numberOfClients = AppReport.getNumberOfUsers(investingApp);
        //Then
        assertEquals(3, numberOfClients);
    }

    @Test
    public void getNumberOfAccounts() {
        int numberOfAccounts = AppReport.getNumberOfTrades(investingApp);
        assertEquals(5, numberOfAccounts);
    }

    @Test
    public void getClientsSorted() {
        TreeSet<User> clientsSorted = (TreeSet<User>) AppReport.getUsersSorted(investingApp);
        assertEquals(3, clientsSorted.size());
        assertEquals("Alex", clientsSorted.first().getName());
    }

    @Test
    public void getTotalSumInAccounts() {
        double totalSumInAccounts = AppReport.getTotalSumInvestedInStocks(investingApp);

        assertEquals(4900, totalSumInAccounts, 0.2);
    }

//    @Test
//    public void getAccountsSortedBySum() {
//        TreeSet<Account> accountsSortedBySum = (TreeSet<Account>) AppReport.getUsersSortedByInvestedSum(investingApp);
//
//        assertEquals(500, accountsSortedBySum.first().getBalance(), 0.1);
//        assertEquals(2000, accountsSortedBySum.last().getBalance(), 0.1);
//    }
//
////    @Test
////    public void getBankCreditSum() {
////        double bankCreditSum = AppReport.getInvestmentSum(investingApp);
////        assertEquals(400, bankCreditSum, 0.200);
////    }
////
////    @Test
////    public void getCustomerAccounts() {
////        User user1 = new User("Alex", Gender.MALE, "Bucuresti");
////
////        Map<User, Collection<Account>> customerAccounts = AppReport.getUsersTrades(investingApp);
////
////        assertEquals(3, customerAccounts.keySet().size());
////        assertTrue(customerAccounts.containsKey(user1));
////        assertEquals(2, customerAccounts.get(user1).size());
////    }

//    @Test
//    public void getClientsByCity() {
//        TreeMap<String, List<User>> clientsByCity = (TreeMap<String, List<User>>) AppReport.getUsersByCity(investingApp);
//        service.close();
//        assertEquals(2, clientsByCity.keySet().size());
//        assertEquals(2, clientsByCity.get("Bucuresti").size());
//        assertEquals("Bucuresti", clientsByCity.firstEntry().getKey());
//    }

//
//    private static List<User> generateClients() {
//        User user1 = new User("Alex", Gender.MALE, "Bucuresti");
//        user1.getAccount().deposit(1000.0);
//        User user2 = new User("Cosmin", Gender.MALE, "Ploiesti");
//        user2.getAccount().deposit(200.0);
//        User user3 = new User("Edward", Gender.MALE, "Bucuresti");
//        user3.getAccount().deposit(2000.0);
//
//        return List.of(user1, user2, user3);
//    }
}
