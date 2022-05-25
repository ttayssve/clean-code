import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FreightTest {

    @Test
    @DisplayName("Test for freight calculate of a item")
    void calculateFreight() {
        Dimension dimension = Dimension.builder()
                .height(100.0)
                .width(30.0)
                .length(10.0)
                .build();
        Item item = Item.builder()
                .idItem(1)
                .category("Category A")
                .description("A")
                .price(BigDecimal.valueOf(1000.0))
                .dimension(dimension)
                .weight(3)
                .build();
        Freight freight = Freight.builder().build();
        freight.addItem(item.getVolume(), item.getDensity(), 2);
        assertEquals(freight.getTotal(), BigDecimal.valueOf(60.0));
    }

    @Test
    @DisplayName("Test for freight calculate minimum")
    void calculateFreightMinimum() {
        Dimension dimension = Dimension.builder()
                .height(10.0)
                .width(10.0)
                .length(10.0)
                .build();
        Item item = Item.builder()
                .idItem(1)
                .category("Category A")
                .description("A")
                .price(BigDecimal.valueOf(1000.0))
                .dimension(dimension)
                .weight(0.9)
                .build();
        Freight freight = Freight.builder().build();
        freight.addItem(item.getVolume(), item.getDensity(), 1);
        assertEquals(freight.getTotal(), BigDecimal.valueOf(10.0));
    }
}
