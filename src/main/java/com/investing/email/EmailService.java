package com.investing.email;

import com.investing.exceptions.EmailException;


public class EmailService implements Runnable {

    private final Queue emailQueue = new Queue();
    private Thread thread;
    private boolean closed;
    private int sentEmails = 0;

    public EmailService() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        Email email;
        while (true) {
            if (closed) {
                return;
            }

            synchronized (emailQueue) {
                try {
                    emailQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
            }

            if ((email = emailQueue.get()) != null) {
                sendEmail(email);
            }
        }
    }

//    public int getSentEmails() {
//        return sentEmails;
//    }

    private void sendEmail(Email email) {
        System.out.println(email);
        sentEmails++;
    }

    public void sendNotificationEmail(Email email) throws EmailException {
        if (!closed) {
            synchronized (emailQueue) {
                emailQueue.add(email);
                emailQueue.notifyAll();
            }
        } else
            throw new EmailException("Mailbox is closed!");
    }

    public void close() {
        closed = true;
        synchronized (emailQueue) {
            emailQueue.notify();
        }
    }

}
