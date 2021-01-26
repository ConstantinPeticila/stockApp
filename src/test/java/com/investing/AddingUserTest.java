package com.investing;

import com.investing.domain.Gender;
import com.investing.domain.InvestingApp;
import com.investing.domain.User;
import com.investing.email.EmailService;
import com.investing.email.NotificationService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AddingUserTest {

    private static InvestingApp investingApp;

    @Before
    public void setInvestingApp(){
        investingApp = new InvestingApp();
        investingApp.setServices(new EmailService(), new NotificationService());
    }

    public User generateUser(){
        User user = new User("Smith John", Gender.MALE);
        user.getAccount().deposit(100);
        return user;
    }

    @Test
    public void hashCode_ShouldBeEquals_forTwoEqualsClients() {
        //Given
        User firstUser = generateUser();
        User secondUser = generateUser();
        //When
        assertTrue(firstUser.equals(secondUser));
        //Then
        assertEquals(firstUser.hashCode(), secondUser.hashCode());
    }
}
