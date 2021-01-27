package com.investing.email;

import com.investing.domain.User;

import java.io.Serializable;
import java.util.ArrayList;

public class Email implements Serializable {

    private static final long serialVersionUID = -3686472195559526951L;
    private User from;
    private User to;
    private String title, body;

    public User getFrom() {
        return from;
    }

    public Email setFrom(User from) {
        this.from = from;
        return this;
    }

    public User getTo() {
        return to;
    }

    public Email setTo(User to) {
        this.to = to;
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
        return "SEND EMAIL:" + "\n" +
                "               From: " + getFrom().getName() + "\n" +
                "               To: " + getTo().getName() + "\n" +
                "               Title: " + getTitle() + "\n" +
                "               Body: " + getBody() + "\n";
    }
}
