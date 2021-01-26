package com.investing.email;

import com.investing.domain.User;

import java.io.Serializable;
import java.util.ArrayList;

public class Email  implements Serializable {

    private static final long serialVersionUID = -3686472195559526951L;
    private User from;
    private ArrayList<User> to, copy;
    private String title, body;

    public User getFrom() {
        return from;
    }

    public Email setFrom(User from) {
        this.from = from;
        return this;
    }

    public ArrayList<User> getTo() {
        return to;
    }

    public Email setTo(ArrayList<User> to) {
        this.to = to;
        return this;
    }

    public Email setTo(User to) {
        ArrayList<User> toList = new ArrayList<>();
        toList.add(to);
        setTo(toList);
        return this;
    }

    public ArrayList<User> getCopy() {
        return copy;
    }

    public Email setCopy(ArrayList<User> copy) {
        this.copy = copy;
        return this;
    }

    public Email setCopy(User copy) {
        ArrayList<User> copyList = new ArrayList<>();
        copyList.add(copy);
        setCopy(copyList);
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Email setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getBody() {
        return body;
    }

    public Email setBody(String body) {
        this.body = body;
        return this;
    }

    @Override
    public String toString() {
        ArrayList<User> users = getTo();
        StringBuilder usersTo = new StringBuilder();
        for (User c: users) {
            usersTo.append(c);
        }

        users = getCopy();
        StringBuilder clientsCopy = new StringBuilder();
        for (User c: users) {
            clientsCopy.append(c);
        }

        return "SEND EMAIL:" + "\n" +
                "From: " + getFrom() +
                "To: " + usersTo +
                "Copy: " + clientsCopy +
                "Title: " + getTitle() + "\n" +
                "Body: " + getBody() + "\n";
    }
}
