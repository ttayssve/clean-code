import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class Item {
    private int idItem;
    private String category;
    private String description;
    private BigDecimal price;
    private double height;
    private double width;
    private double depth;
    private double weight;
}
