package com.investing.email;

import java.io.Serializable;

public class EmailService implements Runnable, Serializable {

    private static final long serialVersionUID = -6872857384878095572L;
    private final Queue emailQueue = new Queue();
    private boolean closed;
    private int sentEmails = 0;

    public EmailService() {
        new Thread(this).start();
    }

    @Override
    public void run() {
        Email email;
        while (true) {
            if(closed) {
                return;
            }

            if ((email = emailQueue.get()) != null) {
                sendEmail(email);
            }
            try {
                synchronized(emailQueue) {
                    emailQueue.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }

        }
    }

    public int getSentEmails() {
        return sentEmails;
    }

    private void sendEmail(Email email) {
        System.out.println(email);
        sentEmails++;
    }

    public void sendNotificationEmail(Email email) throws EmailException {
        if (!closed) {
            emailQueue.add(email);
            synchronized(emailQueue) {
                emailQueue.notify();
            }
        } else
            throw new EmailException("Mailbox is closed!");
    }

    public void close() {
        closed = true;
        synchronized(emailQueue) {
            emailQueue.notify();
        }
    }

}
