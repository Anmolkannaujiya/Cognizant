public class PaymentService {

    public void makePayment(double amount) {
        Logger logger = Logger.getInstance();
        logger.log("Payment of ₹" + amount + " successful.");
    }
}