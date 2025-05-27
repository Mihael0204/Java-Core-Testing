package school.sorokin.javacore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderServiceTest {

    private OrderRepository orderRepository;
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        orderRepository = Mockito.mock(OrderRepository.class);
        orderService = new OrderService(orderRepository);
    }

    @Test
    public void testProcessOrder_Successful() {
        Order order = TestHelper.getDefaultOrder();
        Mockito.when(orderRepository.saveOrder(order)).thenReturn(1);

        String result = orderService.processOrder(order);

        assertEquals("Order processed successfully", result);

        Mockito.verify(orderRepository, Mockito.times(1)).saveOrder(order);
    }

    @Test
    public void testProcessOrder_Failed() {
        Order order = TestHelper.getDefaultOrder();
        Mockito.when(orderRepository.saveOrder(order)).thenThrow(new RuntimeException("Database error"));

        String result = orderService.processOrder(order);

        assertEquals("Order processing failed", result);

        Mockito.verify(orderRepository, Mockito.times(1)).saveOrder(order);
    }

    @Test
    public void shouldCorrectlyCalculateTotalPrice() {
        Order order = TestHelper.getOrderForTesting();
        Mockito.when(orderRepository.findOrderById(13)).thenReturn(Optional.of(order));

        double result = orderService.calculateTotal(13);

        assertEquals(300.0, result);

        Mockito.verify(orderRepository, Mockito.times(1)).findOrderById(13);
    }

    @Test
    public void shouldCorrectlyCalculateTotalPrice_WhenOrderNotFound() {
        Mockito.when(orderRepository.findOrderById(13)).thenReturn(Optional.empty());

        double result = orderService.calculateTotal(13);

        assertEquals(0.0, result);

        Mockito.verify(orderRepository, Mockito.times(1)).findOrderById(13);
    }

    @Test
    public void shouldCorrectlyCalculateTotalPrice_ZeroQuantity() {
        Order order = TestHelper.getZeroQuantityOrder();
        Mockito.when(orderRepository.findOrderById(5)).thenReturn(Optional.of(order));

        double result = orderService.calculateTotal(5);

        assertEquals(0.0, result);

        Mockito.verify(orderRepository, Mockito.times(1)).findOrderById(5);
    }

    @Test
    public void shouldCorrectlyCalculateTotalPrice_ZeroPrice() {
        Order order = TestHelper.getZeroPriceOrder();
        Mockito.when(orderRepository.findOrderById(5)).thenReturn(Optional.of(order));

        double result = orderService.calculateTotal(5);

        assertEquals(0.0, result);

        Mockito.verify(orderRepository, Mockito.times(1)).findOrderById(5);
    }
}