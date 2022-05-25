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
        order.addItem(Item.builder().idItem(1).category("Category A").description("A").price(BigDecimal.valueOf(1000.0)).build(), 1);
        order.addItem(Item.builder().idItem(2).category("Category B").description("B").price(BigDecimal.valueOf(2000.0)).build(), 2);
        order.addItem(Item.builder().idItem(3).category("Category C").description("C").price(BigDecimal.valueOf(3000.0)).build(), 3);
        BigDecimal total = order.getTotal();
        assertEquals(total, BigDecimal.valueOf(14000.0));
    }

    @Test
    @DisplayName("Test to create an order with three items with discount coupon")
    void createOrderWithCoupon() {
        ZoneId zoneIdDefault  = ZoneId.of("America/Sao_Paulo");
        ZonedDateTime orderIssueDate = LocalDateTime.parse("2022-01-01T12:00:00",
                DateTimeFormatter.ISO_DATE_TIME).atZone(zoneIdDefault);
        Order order = new Order("04778667190", orderIssueDate);
        order.addItem(Item.builder().idItem(1).category("Category A").description("A").price(BigDecimal.valueOf(1000.0)).build(), 1);
        order.addItem(Item.builder().idItem(2).category("Category B").description("B").price(BigDecimal.valueOf(2000.0)).build(), 2);
        order.addItem(Item.builder().idItem(3).category("Category C").description("C").price(BigDecimal.valueOf(3000.0)).build(), 3);
        ZonedDateTime couponExpirationDate = LocalDateTime.parse("2099-01-01T12:00:00",
                DateTimeFormatter.ISO_DATE_TIME).atZone(zoneIdDefault);
        Coupon coupon = new Coupon("COUPON10", 10.0, couponExpirationDate);
        order.addCoupon(coupon);
        assertEquals(order.getTotal(), BigDecimal.valueOf(12600.0));
    }

    @Test
    @DisplayName("Test to create an order with three items with expired discount coupon")
    void createOrderWithExpiredCoupon() {
        ZoneId zoneIdDefault  = ZoneId.of("America/Sao_Paulo");
        ZonedDateTime issueDate = LocalDateTime.parse("2022-01-01T12:00:00",
                DateTimeFormatter.ISO_DATE_TIME).atZone(zoneIdDefault);
        Order order = new Order("04778667190", issueDate);
        order.addItem(Item.builder().idItem(1).category("Category A").description("A").price(BigDecimal.valueOf(1000.0)).build(), 1);
        order.addItem(Item.builder().idItem(2).category("Category B").description("B").price(BigDecimal.valueOf(2000.0)).build(), 2);
        order.addItem(Item.builder().idItem(3).category("Category C").description("C").price(BigDecimal.valueOf(3000.0)).build(), 3);

        ZonedDateTime couponExpirationDate = LocalDateTime.parse("2021-01-01T12:00:00",
                DateTimeFormatter.ISO_DATE_TIME).atZone(zoneIdDefault);
        Coupon coupon = new Coupon("COUPON10", 10.0, couponExpirationDate);
        order.addCoupon(coupon);
        assertEquals(order.getTotal(), BigDecimal.valueOf(14000.0));
    }

    @Test
    @DisplayName("Test create an order with three items and calculating shipping")
    void createOrderWithThreeItemsAndFreightCalculate() {
        Order order = new Order("04778667190", null);
        order.addItem(Item.builder().idItem(1).category("Category A").description("A").price(BigDecimal.valueOf(1000.0))
                .dimension(Dimension.builder().height(100.0).width(30.0).length(10.0).build()).weight(3).build(), 1);
        order.addItem(Item.builder().idItem(2).category("Category B").description("B").price(BigDecimal.valueOf(5000.0)).
                dimension(Dimension.builder().height(100.0).width(50.0).length(50.0).build()).weight(20).build(), 1);
        order.addItem(Item.builder().idItem(3).category("Category C").description("C").price(BigDecimal.valueOf(30.0)).
                dimension(Dimension.builder().height(10.0).width(10.0).length(10.0).build()).weight(1).build(), 3);
        BigDecimal total = order.getTotal();
        assertEquals(total, BigDecimal.valueOf(6350.0));
    }

    @Test
    @DisplayName("Test create an order with three items and calculating shipping")
    void createOrderWithThreeItemsAndFreightCalculateMinimum() {
        Order order = new Order("04778667190", null);
        order.addItem(Item.builder().idItem(3).category("Category C").description("C").price(BigDecimal.valueOf(30.0)).
                dimension(Dimension.builder().height(10.0).width(10.0).length(10.0).build()).weight(0.9).build(), 1);
        BigDecimal total = order.getTotal();
        assertEquals(total, BigDecimal.valueOf(40.0));
    }
}
