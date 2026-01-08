abstract class OrderProcessTemplate {
    public final void processOrder () {
        selectItem();
        makePayment();
        deliverOrPickup();
        notifyUser();
    }
    abstract void selectItem();
    abstract void makePayment();
    abstract void deliverOrPickup();

    void notifyUser () {
        System.out.println("Sending default notification");
    }
}

class OnlineOrder extends OrderProcessTemplate {
    @Override
    void selectItem () {
        System.out.println("Selecting item online");
    }

    @Override
    void makePayment () {
        System.out.println("Making online payment");
    }

    @Override
    void deliverOrPickup () {
        System.out.println("Delivering to home");
    }

    @Override
    void notifyUser () {
        System.out.println("Sending Email");
    }
}

class StoreOrder extends OrderProcessTemplate {
    @Override
    void selectItem () {
        System.out.println("Selecting item in store");
    }

    @Override
    void makePayment () {
        System.out.println("Paying at counter");
    }

    @Override
    void deliverOrPickup () {
        System.out.println("Store pickup");
    }

    @Override
    void notifyUser () {
        System.out.println("Sending sms");
    }
}

public class Main {
    public static void main(String[] args) {
        OrderProcessTemplate online = new OnlineOrder();
        online.processOrder();

        OrderProcessTemplate store = new StoreOrder();
        store.processOrder();
    }
}