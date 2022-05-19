import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private Cpf cpf;
    private List<OrderItem> orderItems;

    public Order(String cpf) {
        this.cpf = new Cpf(cpf);
        this.orderItems = new ArrayList<>();
    }

    public void addItem(Item item, int quantity) {
        this.orderItems.add(new OrderItem(item.getIdItem(), item.getPrice(), quantity));
    }

    public BigDecimal getTotal() {
        return this.orderItems.stream()
                .map(OrderItem::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Cpf getCpf() {
        return cpf;
    }

    public void setCpf(Cpf cpf) {
        this.cpf = cpf;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
