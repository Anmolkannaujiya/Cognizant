package singleton;

public class OrderService {

    public void createOrder(int orderId) {
        Logger logger = Logger.getInstance();
        logger.log("Order #" + orderId + " created.");
    }
}