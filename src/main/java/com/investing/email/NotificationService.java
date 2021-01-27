package com.investing.email;
import com.investing.domain.InvestingApp;
import com.investing.exceptions.NotificationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class NotificationService implements Runnable {

    Logger logger = LoggerFactory.getLogger(NotificationService.class);

    private final NotifQueue notifQueue = new NotifQueue();
    private boolean closed;
    private int sentNotifications = 0;

    public NotificationService() {
        new Thread(this).start();
    }

    @Override
    public void run() {
        Notification notification;
        while (true) {
            if (closed) {
                return;
            }
            try {
                synchronized (notifQueue) {
                    notifQueue.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }

            if ((notification = notifQueue.get()) != null) {
                sendNotification(notification);
            }

        }
    }

    private void sendNotification(Notification notification) {
       logger.info(notification.toString());
        sentNotifications++;
    }

    public void sendNotificationEmail(Notification notification) throws NotificationException {
        if (!closed) {
            notifQueue.add(notification);
            synchronized (notifQueue) {
                notifQueue.notify();
            }
        } else
            throw new NotificationException("Mailbox is closed!");
    }

    public void close() {
        closed = true;
        synchronized (notifQueue) {
            notifQueue.notify();
        }
    }

}
