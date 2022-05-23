import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class Order {
    private Cpf cpf;
    private Coupon coupon;
    private List<OrderItem> orderItems;
    private double freight;
    private ZonedDateTime issueDate;

    public Order(String cpf, ZonedDateTime issueDate) {
        this.cpf = new Cpf(cpf);
        this.orderItems = new ArrayList<>();
        this.issueDate = issueDate;
    }

    public void addItem(Item item, int quantity) {
        this.orderItems.add(new OrderItem(item.getIdItem(), item.getPrice(), quantity));
    }

    public void addCoupon(Coupon coupon) {
        if (!coupon.isExpired(this.issueDate)) {
            this.coupon = coupon;
        }
    }

    public BigDecimal getTotal() {
        BigDecimal total = this.orderItems.stream()
                .map(OrderItem::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        if (Objects.nonNull(this.coupon)) {
            total = total.subtract(this.coupon.calculateDiscount(total));
        }
        return total;
    }
}
