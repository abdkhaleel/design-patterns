package factory;

import notification.Notification;
import notification.SMSNotification;

public class SMSFactory extends NotificationFactory{
    @Override
    public Notification createNotification() {
        return new SMSNotification();
    }
}
