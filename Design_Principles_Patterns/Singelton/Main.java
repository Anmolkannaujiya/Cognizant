public class Main {

    public static void main(String[] args) {

        UserService user = new UserService();
        PaymentService payment = new PaymentService();
        OrderService order = new OrderService();

        user.login("Anmol");

        payment.makePayment(599);

        order.createOrder(101);

        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        System.out.println(logger1 == logger2);
    }
}