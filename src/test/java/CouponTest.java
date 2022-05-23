import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class CouponTest {

    @Test
    @DisplayName("Test for create discount coupon")
    void createDiscountCoupon() {
        Coupon coupon = Coupon.builder()
                .code("COUPON10")
                .percentage(10.0)
                .build();
        boolean isExpired = coupon.isExpired(null);
        assertFalse(isExpired);
    }

    @Test
    @DisplayName("Test for calculate discount")
    void calculateDiscount() {
        Coupon coupon = Coupon.builder()
                .code("COUPON10")
                .percentage(10.0)
                .build();
        assertEquals(new BigDecimal("10.0"),
                coupon.calculateDiscount(new BigDecimal("100.0")));
    }

    @Test
    @DisplayName("Test for create discount coupon expired")
    void createDiscountCouponExpired() {
        ZoneId zoneIdDefault  = ZoneId.of("America/Sao_Paulo");
        ZonedDateTime couponExpirationDate = LocalDateTime.parse("2021-01-01T12:00:00",
                DateTimeFormatter.ISO_DATE_TIME).atZone(zoneIdDefault);
        Coupon coupon = Coupon.builder()
                .code("COUPON10")
                .percentage(10.0)
                .couponExpirationDate(couponExpirationDate)
                .build();
        ZonedDateTime dateAfterExpiration = LocalDateTime.parse("2022-01-01T12:00:00",
                DateTimeFormatter.ISO_DATE_TIME).atZone(zoneIdDefault);
        boolean isExpired = coupon.isExpired(dateAfterExpiration);
        assertTrue(isExpired);
    }
}
