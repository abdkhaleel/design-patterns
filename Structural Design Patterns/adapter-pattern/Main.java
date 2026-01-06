interface PaymentProcessor {
    void pay(int amount);
}

class PaytmGateway {
    void makePayment (double amount) {
        System.out.println("Paid Rs." + amount + " via Paytm");
    }
}

class PaytmAdapter implements PaymentProcessor {
    private PaytmGateway paytm;

    public PaytmAdapter(PaytmGateway paytm) {
        this.paytm = paytm;
    }

    @Override
    public void pay (int amount) {
        paytm.makePayment((double) amount);
    }
}

class PaymentService {
    private PaymentProcessor processor;

    public PaymentService (PaymentProcessor processor) {
        this.processor = processor;
    }

    public void process (int amount) {
        processor.pay(amount);
    }
}

public class Main {
    public static void main(String[] args) {

        PaymentProcessor adapter;
        PaymentService service;

        PaytmGateway paytm = new PaytmGateway();
        adapter = new PaytmAdapter(paytm);
        service = new PaymentService(adapter);
        service.process(500);


        //Gpay 

        GpayGateway gpay = new GpayGateway();
        adapter = new GpayAdapter(gpay);
        service = new PaymentService(adapter);
        service.process(2000);
        
    }
}

//Adding Gpay

class GpayGateway {
    void makePayment (double amount) {
        System.out.println("Paid Rs." + amount + " via Google Pay");
    }
}

class GpayAdapter implements PaymentProcessor {
    private GpayGateway gpay;

    public GpayAdapter(GpayGateway gpay) {
        this.gpay = gpay;
    }

    @Override
    public void pay (int amount) {
        gpay.makePayment((double) amount);
    }
}