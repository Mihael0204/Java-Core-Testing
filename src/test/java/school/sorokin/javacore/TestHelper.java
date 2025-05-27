package school.sorokin.javacore;

public class TestHelper {
    public static Order getDefaultOrder() {
        return new Order(1, "Apple", 54, 12.0);
    }

    public static Order getOrderForTesting() {
        return new Order(13, "Keyboard", 3, 100.0);
    }

    public static Order getZeroQuantityOrder() {
        return new Order(5, "Orange", 0, 22.0);
    }

    public static Order getZeroPriceOrder() {
        return new Order(5, "Melon", 55, 0.0);
    }
}
