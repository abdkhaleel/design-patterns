interface PaymentStrategy {
    void pay(int amount);
}

class CardPayment implements PaymentStrategy {
    @Override
    public void pay (int amount) {
        System.out.println("Paid " + amount + " using Card");
    }
}

class UpiPayment implements PaymentStrategy {
    @Override
    public void pay (int amount) {
        System.out.println("Paid " + amount + " using UPI");
    }
}

class WalletPayment implements PaymentStrategy {
    @Override
    public void pay (int amount) {
        System.out.println("Paid " + amount + " using Wallet");
    }
}

class PaymentService {
    private PaymentStrategy strategy;

    public void setStrategy (PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void pay (int amount) {
        strategy.pay(amount);
    }
}

public class Main {
    public static void main(String[] args) {
        PaymentService service = new PaymentService();

        service.setStrategy(new CardPayment());
        service.pay(10);

        service.setStrategy(new UpiPayment());
        service.pay(100);

        service.setStrategy(new WalletPayment());
        service.pay(1000);
    }
}