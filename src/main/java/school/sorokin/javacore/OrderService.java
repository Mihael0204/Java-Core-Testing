package school.sorokin.javacore;

public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public String processOrder(Order order) {
        try {
            int resultId = orderRepository.saveOrder(order);
            return resultId > 0 ? "Order processed successfully" : "Order processing failed";
        } catch (Exception e) {
            return "Order processing failed";
        }
    }

    public double calculateTotal(int id) {
        return orderRepository.findOrderById(id)
                .map(Order::getTotalPrice)
                .orElse(0.0);
    }
}
