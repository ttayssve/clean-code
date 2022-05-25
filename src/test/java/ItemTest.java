import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemTest {

    @Test
    @DisplayName("Test for create item with dimension and calculator volume")
    void createItemWithDimensionAndGetVolume() {
        Item item = Item.builder()
                .idItem(1)
                .category("Category A")
                .description("A")
                .price(BigDecimal.valueOf(1000.0))
                .dimension(fromDimension())
                .build();
        assertEquals(item.getVolume(), 0.03);
    }

    @Test
    @DisplayName("Test for create item with dimension and calculator density")
    void createItemWithDimensionAndGetDensity() {
        Item item = Item.builder()
                .idItem(1)
                .category("Category A")
                .description("A")
                .price(BigDecimal.valueOf(1000.0))
                .dimension(fromDimension())
                .weight(3)
                .build();
        assertEquals(100.0, item.getDensity());
    }

    private Dimension fromDimension() {
        return Dimension.builder()
                .height(100.0)
                .width(30.0)
                .length(10.0)
                .build();
    }
}
