package com.investing.tests;

import com.investing.domain.Gender;
import com.investing.domain.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void equals_ShouldReturnTrue_IfIsReceivedAClientWithSameValues() {
        //Given
        User user = generateClient();
        //When
        boolean clientsAreEquals = user.equals(generateClient());
        //Then
        assertTrue(clientsAreEquals);
    }

    @Test
    public void equals_ShouldReturnFalse_IfIsReceivedAClientWithDifferentValues() {
        //Given
        User user = generateClient();
        User userWithDifferentAccounts = new User("Smith John", Gender.FEMALE);
        //When
        boolean clientsAreEquals = user.equals(userWithDifferentAccounts);
        //Then
        assertFalse(clientsAreEquals);
    }

    @Test
    public void hashCode_ShouldBeEquals_forTwoEqualsClients() {
        //Given
        User firstUser = generateClient();
        User secondUser = generateClient();
        //When
        assertTrue(firstUser.equals(secondUser));
        //Then
        assertEquals(firstUser.hashCode(), secondUser.hashCode());
    }

    private User generateClient(){
        User user = new User("Smith John", Gender.MALE);
        user.getAccount().deposit(100);
        return user;
    }
}