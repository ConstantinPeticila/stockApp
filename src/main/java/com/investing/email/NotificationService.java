package com.investing.email;
import com.investing.exceptions.NotificationException;


public class NotificationService implements Runnable {

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
        System.out.println(notification);
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
