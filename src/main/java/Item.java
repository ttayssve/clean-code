import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@Builder
public class Item {
    private int idItem;
    private String category;
    private String description;
    private BigDecimal price;
    private Dimension dimension;
    private double weight;

    public double getVolume() {
        if (Objects.nonNull(dimension)) return this.dimension.getVolume();
        return 0.0;
    }

    public double getDensity() {
        if (Objects.nonNull(this.dimension)) return this.weight / this.dimension.getVolume();
        return 0.0;
    }
}
