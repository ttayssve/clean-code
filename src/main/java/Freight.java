import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@Builder
public class Freight {
    private final static int DISTANCE = 1000;
    private final static double MIN_FREIGHT = 10.0;
    private BigDecimal total;

    public void addItem(double volume, double density, int quantity) {
        if (Objects.isNull(total)) this.total = BigDecimal.ZERO;
        double freight = (volume * DISTANCE * (density / 100)) * quantity;
        this.total = this.total.add(BigDecimal.valueOf(freight));
    }

    public BigDecimal getTotal() {
        if (isMinimumFreight()) {
            return BigDecimal.valueOf(MIN_FREIGHT);
        }
        return this.total;
    }

    private boolean isMinimumFreight() {
        double total = this.total.doubleValue();
        return total > 0 && total < MIN_FREIGHT;
    }
}
