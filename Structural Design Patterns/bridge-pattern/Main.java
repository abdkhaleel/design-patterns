interface NotificationSender {
    void send (String message);
}

class EmailSender implements NotificationSender {
    @Override
    public void send (String message) {
        System.out.println("Sending Email: " + message);
    }
}

class SmsSender implements NotificationSender {
    @Override
    public void send (String message) {
        System.out.println("Sending SMS: " + message);
    }
}

abstract class Notification {
    protected NotificationSender sender;

    public Notification (NotificationSender sender) {
        this.sender = sender;
    }

    abstract void notifyUser (String message);
}

class AlertNotification extends Notification {
    public AlertNotification (NotificationSender sender) {
        super(sender);
    }

    @Override
    public void notifyUser (String message) {
        sender.send("ALERT: " + message);
    }
}

class ReminderNotification extends Notification {
    public ReminderNotification (NotificationSender sender) {
        super(sender);
    } 

    @Override
    public void notifyUser (String message) {
        sender.send("REMINDER: " + message);
    }
}

public class Main {
    public static void main (String[] args) {
        // NotificationSender email = new EmailSender();
        // NotificationSender sms = new SmsSender();

        // Notification alertEmail = new AlertNotification(email);
        // alertEmail.notifyUser("Server is DOWN!!");

        // Notification remainderSms = new ReminderNotification(sms);
        // remainderSms.notifyUser("You have a meet at 10AM");

        // //Using whatsapp and promotion
        // NotificationSender whatsapp = new WhatsappSender();
        // Notification promotion = new PromotionNotification(whatsapp);
        // promotion.notifyUser("Our products are best");


        NotificationSender sender;
        Notification notification;

        //Using email with promotion
        sender = new EmailSender();
        notification = new PromotionNotification(sender);
        notification.notifyUser("Our products are Best");

        //Using sms with Alert
        sender = new SmsSender();
        notification = new AlertNotification(sender);
        notification.notifyUser("Your validity is expiring soon!!");

        //Using whatsapp with remainder
        sender = new WhatsappSender();
        notification = new ReminderNotification(sender);
        notification.notifyUser("You have pending Backup");
    }
}

// new whatsapp and promotion

class WhatsappSender implements NotificationSender {
    @Override
    public void send (String message) {
        System.out.println("Sending Whatsapp: " + message);
    }
}

class PromotionNotification extends Notification {

    public PromotionNotification (NotificationSender sender) {
        super(sender);
    }
    @Override
    public void notifyUser (String message) {
        sender.send("PROMOTION: " + message);
    }
}