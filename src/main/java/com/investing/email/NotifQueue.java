package com.investing.email;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class NotifQueue implements Serializable {

    private static final long serialVersionUID = -367534955230149745L;

    private List<Notification> notifications = Collections.synchronizedList(new LinkedList<>());

    public void add(Notification notification) {
        notifications.add(notification);
    }

    public Notification get() {
        if (notifications.size() > 0) {
            return notifications.remove(notifications.size() - 1);
        }

        return null;
    }

}
