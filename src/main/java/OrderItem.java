import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class OrderItem {
    private int idItem;
    private BigDecimal price;
    private int quantity;

    public BigDecimal  getTotal() {
        return BigDecimal.valueOf(this.quantity).multiply(this.price);
    }
}
