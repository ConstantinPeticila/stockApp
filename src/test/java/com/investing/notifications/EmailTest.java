package com.investing.notifications;

import com.investing.domain.User;
import com.investing.email.*;
import com.investing.exceptions.EmailException;
import org.junit.jupiter.api.Test;


import static com.investing.domain.Gender.MALE;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmailTest {
    @Test
    public void noEmail() {
        Queue queue = new Queue();
        assertNull(queue.get());
    }

    @Test
    public void badNotificationTest() {
        EmailService emailService = new EmailService();
        emailService.close();
        assertThrows(EmailException.class, () -> {
            emailService.sendNotificationEmail(new Email());
        });

    }

    @Test
    public void NotificationMembersTest() {
        User mihai = new User("Mihai", MALE);
        User system = new User("System", MALE);
        Email email = new Email().setFrom(system).setTo(mihai).setTitle(" user added notification").setBody("User added " + mihai);

        assertEquals(system, email.getFrom());
        assertEquals(mihai, email.getTo());
        assertTrue(email.getBody().contains("Mihai"));
        assertTrue(email.getTitle().contains("user"));
        assertTrue(email.toString().contains("SEND EMAIL"));

    }
}
