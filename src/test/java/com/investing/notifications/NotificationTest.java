package com.investing.notifications;

import com.investing.domain.Stock;
import com.investing.domain.User;
import com.investing.email.Notification;
import com.investing.email.NotificationService;
import com.investing.email.Queue;
import com.investing.exceptions.NotificationException;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static com.investing.domain.Gender.MALE;
import static org.junit.jupiter.api.Assertions.*;

public class NotificationTest {

    @Test
    public void noNotification() {
       Queue notifQueue = new Queue();
        assertNull(notifQueue.get());
    }

    @Test
    public void badNotificationTest() {
        NotificationService notificationService = new NotificationService();
        notificationService.close();
        assertThrows(NotificationException.class, () -> {
            notificationService.sendNotificationEmail(new Notification());
        });

    }

    @Test
    public void NotificationMembersTest() {
        User mihai = new User("Mihai", MALE);
        mihai.getAccount().deposit(300);
        Stock stock = new Stock("NIO", 35.5);
        Notification notification = new Notification().setFrom(stock).setTo(Set.of(mihai)).setTitle("Stock added").setBody("Stock " + stock.getName() + " has been added with value " + stock.getPrice());

        assertEquals(stock, notification.getFrom());
        assertEquals(Set.of(mihai), notification.getTo());
        assertTrue(notification.getBody().contains("NIO"));
        assertTrue(notification.getTitle().contains("Stock"));
        assertTrue(notification.toString().contains("SEND NOTI"));

    }


}
