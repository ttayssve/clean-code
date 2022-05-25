import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DimensionTest {

    @Test
    @DisplayName("Test for create dimension of item")
    void createDimension() {
        Dimension dimension = Dimension.builder()
                .height(100.0)
                .width(30.0)
                .length(10.0)
                .build();
        double volume = dimension.getVolume();
        assertEquals(volume, 0.03);
    }
}
