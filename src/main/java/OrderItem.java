import java.math.BigDecimal;

public class OrderItem {
    private int idItem;
    private BigDecimal price;
    private int quantity;

    public OrderItem(int idItem, BigDecimal price, int quantity) {
        this.idItem = idItem;
        this.price = price;
        this.quantity = quantity;
    }

    public BigDecimal  getTotal() {
        return BigDecimal.valueOf(this.quantity).multiply(this.price);
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
