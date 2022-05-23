import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {

    @Test
    @DisplayName("Test the creation of an order")
    void createOrder() {
        assertDoesNotThrow(() -> new Order("04778667190", null));
    }

    @Test
    @DisplayName("Test creating an order with three items")
    void createOrderWithThreeItems() {
        Order order = new Order("04778667190", null);
        order.addItem(createItem(1, "Category A", "A", "1000.0", 10.0, 10.0, 10.0, 10.0), 1);
        order.addItem(createItem(2, "Category B", "B", "2000.0", 20.0, 20.0, 20.0, 20.0), 2);
        order.addItem(createItem(3, "Category C", "C", "3000.0", 30.0, 30.0, 30.0, 30.0), 3);
        BigDecimal total = order.getTotal();
        assertEquals(total, new BigDecimal("14000.0"));
    }

    @Test
    @DisplayName("Test to create an order with three items with discount coupon")
    void createOrderWithCoupon() {
        ZoneId zoneIdDefault  = ZoneId.of("America/Sao_Paulo");
        ZonedDateTime orderIssueDate = LocalDateTime.parse("2022-01-01T12:00:00",
                DateTimeFormatter.ISO_DATE_TIME).atZone(zoneIdDefault);
        Order order = new Order("04778667190", orderIssueDate);
        order.addItem(createItem(1, "Category A", "A", "1000.0", 10.0, 10.0, 10.0, 10.0), 1);
        order.addItem(createItem(2, "Category B", "B", "2000.0", 10.0, 10.0, 10.0, 10.0), 2);
        order.addItem(createItem(3, "Category C", "C", "3000.0", 10.0, 10.0, 10.0, 10.0), 3);
        ZonedDateTime couponExpirationDate = LocalDateTime.parse("2099-01-01T12:00:00",
                DateTimeFormatter.ISO_DATE_TIME).atZone(zoneIdDefault);
        Coupon coupon = new Coupon("COUPON10", 10.0, couponExpirationDate);
        order.addCoupon(coupon);
        assertEquals(order.getTotal(), new BigDecimal("12600.0"));
    }

    @Test
    @DisplayName("Test to create an order with three items with expired discount coupon")
    void createOrderWithExpiredCoupon() {
        ZoneId zoneIdDefault  = ZoneId.of("America/Sao_Paulo");
        ZonedDateTime issueDate = LocalDateTime.parse("2022-01-01T12:00:00",
                DateTimeFormatter.ISO_DATE_TIME).atZone(zoneIdDefault);
        Order order = new Order("04778667190", issueDate);
        order.addItem(new Item(1, "Category A", "A", new BigDecimal("1000.0"), 10.0, 10.0, 10.0, 5.0), 1);
        order.addItem(new Item(2, "Category B", "B", new BigDecimal("2000.0"), 10.0, 10.0, 10.0, 5.0), 2);
        order.addItem(new Item(3, "Category C", "C", new BigDecimal("3000.0"), 10.0, 10.0, 10.0, 5.0), 3);
        ZonedDateTime couponExpirationDate = LocalDateTime.parse("2021-01-01T12:00:00",
                DateTimeFormatter.ISO_DATE_TIME).atZone(zoneIdDefault);
        Coupon coupon = new Coupon("COUPON10", 10.0, couponExpirationDate);
        order.addCoupon(coupon);
        assertEquals(order.getTotal(), new BigDecimal("14000.0"));
    }

    private Item createItem(int idItem, String category, String description, String price, double height,
                            double width, double depth, double weight) {
        return Item.builder()
                .idItem(idItem)
                .category(category)
                .description(description)
                .price(new BigDecimal(price))
                .height(height)
                .width(width)
                .depth(depth)
                .weight(weight)
                .build();
    }
}
