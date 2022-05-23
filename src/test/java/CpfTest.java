import exception.CpfInvalidException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CpfTest {

    @Test
    @DisplayName("Must test a valid cpf")
    void validateCpf() {
        assertEquals("04778667190", new Cpf("04778667190").cpf());
    }

    @Test
    @DisplayName("Must test cpf with all numbers the same")
    void validateCpfWithAllTheSameNumbers() {
        assertThrows(CpfInvalidException.class, () -> new Cpf("11111111111"));
    }

    @Test
    @DisplayName("Must test cpf with all different numbers")
    void validateCpfWithAllTheDifferentNumbers() {
        assertThrows(CpfInvalidException.class, () -> new Cpf("047.786.671-12"));
    }
}