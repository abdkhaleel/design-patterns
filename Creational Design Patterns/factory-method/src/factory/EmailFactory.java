package factory;

import notification.EmailNotification;
import notification.Notification;

public class EmailFactory extends NotificationFactory{
    @Override
    public Notification createNotification() {
        return new EmailNotification();
    }
}
