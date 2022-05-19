import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderTest {

    @Test
    @DisplayName("Test the creation of an order")
    void createOrder() {
        assertDoesNotThrow(() -> new Order("04778667190"));
    }

    @Test
    @DisplayName("Test creating an order with three items")
    void createOrderWithThreeItems() {
        Order order = new Order("04778667190");
        order.addItem(new Item(1, "Category A", "A", new BigDecimal(1000)), 1);
        order.addItem(new Item(2, "Category B", "B", new BigDecimal(2000)), 2);
        order.addItem(new Item(3, "Category C", "C", new BigDecimal(3000)), 3);
        BigDecimal total = order.getTotal();
        assertEquals(total, new BigDecimal(14000));
    }
}
