

class InventoryService {
    public boolean checkStock (String product) {
        System.out.println("Checking stock for " + product);
        return true;
    }
}

class PaymentService {
    void makePayment (int amount) {
        System.out.println("Payment of Rs." + amount + " successful");
    }
}

class ShippingService {
    void ship (String product) {
        System.out.println("Shipping " + product);
    }
}

class NotificationService {
    void send (String message) {
        System.out.println("Notification sent: " + message);
    }
}

class OrderFacade {
    private final InventoryService inventoryService = new InventoryService();
    private final PaymentService paymentService = new PaymentService();
    private final ShippingService shippingService = new ShippingService();
    private final NotificationService notificationService = new NotificationService();

    public void placeOrder (String product, int amount) {
        if(!inventoryService.checkStock(product)){
            System.out.println("Out of stock");
            return;
        }

        paymentService.makePayment(amount);
        shippingService.ship(product);
        notificationService.send("Your order is confirmed");
    }
}

public class Main {
    public static void main(String[] args) {
        OrderFacade order = new OrderFacade();
        order.placeOrder("iPhone", 150000);
    }
}