package com.investing.email;

import com.investing.domain.Stock;
import com.investing.domain.User;

import java.io.Serializable;
import java.util.ArrayList;

public class Notification  implements Serializable {

    private static final long serialVersionUID = -3686472195559526951L;
    private Stock from;
    private ArrayList<User> to;
    private String title, body;

    public Stock getFrom() {
        return from;
    }

    public Notification setFrom(Stock from) {
        this.from = from;
        return this;
    }

    public ArrayList<User> getTo() {
        return to;
    }

    public Notification setTo(ArrayList<User> to) {
        this.to = to;
        return this;
    }

    public Notification setTo(User to) {
        ArrayList<User> toList = new ArrayList<>();
        toList.add(to);
        setTo(toList);
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
        ArrayList<User> users = getTo();
        StringBuilder usersTo = new StringBuilder();
        for (User c : users) {
            usersTo.append(c);
        }

        return "SEND Notification:" + "\n" +
                "Stock: " + getFrom() +
                "To: " + usersTo +
                "Title: " + getTitle() + "\n" +
                "Body: " + getBody() + "\n";
    }
}