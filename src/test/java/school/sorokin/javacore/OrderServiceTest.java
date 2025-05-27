package school.sorokin.javacore;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
public class OrderServiceTest {

    @Test
    public void testProcessOrder_Successful() {
        OrderRepository orderRepositoryMock = Mockito.mock(OrderRepository.class);
        OrderService orderService = new OrderService(orderRepositoryMock);
        Order order = TestHelper.getDefaultOrder();
        Mockito.when(orderRepositoryMock.saveOrder(order)).thenReturn(1);

        String result = orderService.processOrder(order);

        assertEquals("Order processed successfully", result);

        Mockito.verify(orderRepositoryMock, Mockito.times(1)).saveOrder(order);
    }

    @Test
    public void testProcessOrder_Failed() {
        OrderRepository orderRepositoryMock = Mockito.mock(OrderRepository.class);
        OrderService orderService = new OrderService(orderRepositoryMock);
        Order order = TestHelper.getDefaultOrder();
        Mockito.when(orderRepositoryMock.saveOrder(order)).thenThrow(new RuntimeException("Database error"));

        String result = orderService.processOrder(order);

        assertEquals("Order processing failed", result);

        Mockito.verify(orderRepositoryMock, Mockito.times(1)).saveOrder(order);
    }

    @Test
    public void shouldCorrectlyCalculateTotalPrice() {
        OrderRepository orderRepositoryMock = Mockito.mock(OrderRepository.class);
        OrderService orderService = new OrderService(orderRepositoryMock);
        Order order = TestHelper.getOrderForTesting();
        Mockito.when(orderRepositoryMock.findOrderById(13)).thenReturn(Optional.of(order));

        double result = orderService.calculateTotal(13);

        assertEquals(300.0, result);

        Mockito.verify(orderRepositoryMock, Mockito.times(1)).findOrderById(13);
    }

    @Test
    public void shouldCorrectlyCalculateTotalPrice_WhenOrderNotFound() {
        OrderRepository orderRepositoryMock = Mockito.mock(OrderRepository.class);
        OrderService orderService = new OrderService(orderRepositoryMock);
        Order order = TestHelper.getOrderForTesting();
        Mockito.when(orderRepositoryMock.findOrderById(13)).thenReturn(Optional.empty());

        double result = orderService.calculateTotal(13);

        assertEquals(0.0, result);

        Mockito.verify(orderRepositoryMock, Mockito.times(1)).findOrderById(13);
    }

    @Test
    public void shouldCorrectlyCalculateTotalPrice_ZeroQuantity() {
        OrderRepository orderRepositoryMock = Mockito.mock(OrderRepository.class);
        OrderService orderService = new OrderService(orderRepositoryMock);
        Order order = TestHelper.getZeroQuantityOrder();
        Mockito.when(orderRepositoryMock.findOrderById(5)).thenReturn(Optional.of(order));

        double result = orderService.calculateTotal(5);

        assertEquals(0.0, result);

        Mockito.verify(orderRepositoryMock, Mockito.times(1)).findOrderById(5);
    }

    @Test
    public void shouldCorrectlyCalculateTotalPrice_ZeroPrice() {
        OrderRepository orderRepositoryMock = Mockito.mock(OrderRepository.class);
        OrderService orderService = new OrderService(orderRepositoryMock);
        Order order = TestHelper.getZeroPriceOrder();
        Mockito.when(orderRepositoryMock.findOrderById(5)).thenReturn(Optional.of(order));

        double result = orderService.calculateTotal(5);

        assertEquals(0.0, result);

        Mockito.verify(orderRepositoryMock, Mockito.times(1)).findOrderById(5);
    }
}