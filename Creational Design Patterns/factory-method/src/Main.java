import factory.*;//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        NotificationFactory emailFactory = new EmailFactory();
        emailFactory.sendNotification("Your order has been placed");

        NotificationFactory smsFactory = new SMSFactory();
        smsFactory.sendNotification("Your OTP is 0000");
    }
}