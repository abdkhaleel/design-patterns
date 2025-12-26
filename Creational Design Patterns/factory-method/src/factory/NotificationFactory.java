package factory;

import notification.Notification;

public abstract class NotificationFactory {
    public abstract Notification createNotification();

    public void sendNotification(String message){
        Notification notification = createNotification();

        System.out.println("--- Starting Notification Process ---");
        notification.notifyUser(message);
        System.out.println("--- Notification Process Completed ---\n");
    }
}
