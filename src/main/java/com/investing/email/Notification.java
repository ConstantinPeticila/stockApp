package com.investing.email;

import com.investing.domain.Stock;
import com.investing.domain.User;
import java.util.Set;

public class Notification{

    private Stock from;
    private Set<User> to;
    private String title, body;

    public Stock getFrom() {
        return from;
    }

    public Notification setFrom(Stock from) {
        this.from = from;
        return this;
    }

    public Set<User> getTo() {
        return to;
    }

    public Notification setTo(Set<User> to) {
        this.to = to;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Notification setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getBody() {
        return body;
    }

    public Notification setBody(String body) {
        this.body = body;
        return this;
    }

    @Override
    public String toString() {
       Set<User> users = getTo();
        StringBuilder usersTo = new StringBuilder();
        for (User c : users) {
            usersTo.append(c.getName());
        }

        return "SEND NOTIFICATION:" + "\n" +
                "              Stock: " + getFrom().getName()+ "\n"+
                "              To: " + usersTo+ "\n" +
                "              Title: " + getTitle() + "\n" +
                "              Body: " + getBody() + "\n";
    }
}