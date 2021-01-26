package com.investing.tests;

import com.investing.domain.*;
import com.investing.exceptions.UserExistsException;
import com.investing.email.EmailService;
import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class InvestingAppTest {

    @Test()
    public void addClient_ShouldAddClient_IfDifferentClientIsAdded() throws UserExistsException {
        EmailService service = new EmailService();
        InvestingApp investingApp = new InvestingApp();
        List<User> users = generateClients();
        for (User user : users) {
            investingApp.addUser(user);
        }

        Set<User> appUsers = investingApp.getUsers();
        assertEquals(users.size(), appUsers.size());
        assertTrue(appUsers.contains(users.get(0)));
        assertTrue(appUsers.contains(users.get(1)));
        service.close();
    }

    @Test(expected = UserExistsException.class)
    public void addClient_ShouldThrowException_IfSameClientIsAddedTwice() throws UserExistsException {
        EmailService service = new EmailService();
        InvestingApp investingApp = new InvestingApp();
        List<User> users = generateClients();
        for (User user : users) {
            investingApp.addUser(user);
        }
        investingApp.addUser(users.get(0));
        service.close();
    }


    private List<User> generateClients() {
        User user1 = new User("Smith John", Gender.MALE, "Bucuresti");
        user1.getAccount().deposit(1000.0);

        User user2 = new User("Smith Michelle", Gender.FEMALE, "Bucuresti");
        user2.getAccount().deposit(2000.0);

        return List.of(user1, user2);
    }

}
