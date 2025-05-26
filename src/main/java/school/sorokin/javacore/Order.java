package school.sorokin.javacore;

import java.util.Objects;

public class Order {
    private int id;
    private String productName;
    private int quantity;
    private double UnitPrice;

    public Order(int id, String productName, int quantity, double unitPrice) {
        this.id = id;
        this.productName = productName;
        this.quantity = quantity;
        UnitPrice = unitPrice;
    }

    public double getTotalPrice() {
        return UnitPrice * quantity;
    }

    public int getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return UnitPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && quantity == order.quantity && Double.compare(UnitPrice, order.UnitPrice) == 0 && Objects.equals(productName, order.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productName, quantity, UnitPrice);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", UnitPrice=" + UnitPrice +
                '}';
    }
}
