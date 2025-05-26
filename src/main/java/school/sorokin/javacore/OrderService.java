package school.sorokin.javacore;

public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public String processOrder(Order order) {
        int resultId = orderRepository.saveOrder(order);
        return resultId > 0 ? "Order processed succesfully" : "Order proccesing failed";
    }

    public double calculateTotal(int id) {
        return orderRepository.findOrderById(id)
                .map(Order::getTotalPrice)
                .orElse(0.0);
    }
}
