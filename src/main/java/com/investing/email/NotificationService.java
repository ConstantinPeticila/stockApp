package com.investing.email;

import java.io.Serializable;

public class NotificationService implements Runnable, Serializable {

    private static final long serialVersionUID = -6872857384878095572L;
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
            if(closed) {
                return;
            }

            if ((notification = notifQueue.get()) != null) {
                sendNotification(notification);
            }
            try {
                synchronized(notifQueue) {
                    notifQueue.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }

        }
    }

    public int getSentNotifications() {
        return sentNotifications;
    }

    private void sendNotification(Notification notification) {
        System.out.println(notification);
        sentNotifications++;
    }

    public void sendNotificationEmail(Notification notification) throws EmailException {
        if (!closed) {
            notifQueue.add(notification);
            synchronized(notifQueue) {
                notifQueue.notify();
            }
        } else
            throw new EmailException("Mailbox is closed!");
    }

    public void close() {
        closed = true;
        synchronized(notifQueue) {
            notifQueue.notify();
        }
    }

}
