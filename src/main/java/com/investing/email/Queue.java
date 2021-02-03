package com.investing.email;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Queue<T> implements Serializable {

    private static final long serialVersionUID = -367534955230149744L;

    private List<T> emails = Collections.synchronizedList(new LinkedList<>());

    public void add(T t) {
        emails.add(t);
    }

    public T get() {
        if (emails.size() > 0) {
            return emails.remove(emails.size() - 1);
        }
        return null;
    }

}
