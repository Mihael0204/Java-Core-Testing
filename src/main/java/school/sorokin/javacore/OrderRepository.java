package school.sorokin.javacore;

import java.util.Optional;

public interface OrderRepository {
    int saveOrder(Order order);
    Optional<Order> findOrderById(int id);
}
