import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private Cpf cpf;
    private Coupon coupon;
    private List<OrderItem> orderItems;

    public Order(String cpf) {
        this.cpf = new Cpf(cpf);
        this.orderItems = new ArrayList<>();
    }

    public void addItem(Item item, int quantity) {
        this.orderItems.add(new OrderItem(item.getIdItem(), item.getPrice(), quantity));
    }

    public void addCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    public BigDecimal getTotal() {
        BigDecimal total = this.orderItems.stream()
                .map(OrderItem::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return this.coupon != null ? total.subtract(discountValue(total)) : total;
    }

    private BigDecimal discountValue(BigDecimal total) {
        return BigDecimal.valueOf((this.coupon.getPercentage() * total.doubleValue()) / 100);
    }

    public Cpf getCpf() {
        return cpf;
    }

    public void setCpf(Cpf cpf) {
        this.cpf = cpf;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
