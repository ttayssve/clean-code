import exception.CpfInvalidException;

import java.util.Objects;

public record Cpf(String cpf) {
    private final static int FACTOR_DIGIT1 = 10;
    private final static int FACTOR_DIGIT2 = 11;

    public Cpf {
        if (!validateCpf(cpf)) throw new CpfInvalidException("Cpf is not valid.");
    }

    private Boolean validateCpf(String cpf) {
        if (Objects.isNull(cpf)) return false;
        cpf = this.cleanCpf(cpf);
        if (!this.isValidLength(cpf)) return false;
        if (this.hasAllDigitsEqual(cpf)) return false;
        Integer digit1 = this.calculateCheckDigit(cpf, FACTOR_DIGIT1);
        Integer digit2 = this.calculateCheckDigit(cpf, FACTOR_DIGIT2);
        Integer checkDigit = this.extractCheckDigit(cpf);
        Integer calculatedDigit = this.getCalculatedDigit(digit1, digit2);
        return checkDigit.equals(calculatedDigit);
    }

    private Integer getCalculatedDigit(int digit1, int digit2) {
        return Integer.valueOf(String.format("%s%s", digit1, digit2));
    }

    private Integer extractCheckDigit(String cpf) {
        return Integer.valueOf(cpf.substring(cpf.length() - 2));
    }

    private Integer calculateCheckDigit(String cpf, int factor) {
        int total = 0;
        for (int i = 0; i < cpf.length() - 1; i++) {
            if (factor > 1) {
                total += this.getIndexValue(cpf, i) * factor--;
            }
        }
        int rest = total % 11;
        return (rest < 2) ? 0 : 11 - rest;
    }

    private Integer getIndexValue(String cpf, int i) {
        return Integer.valueOf(String.valueOf(cpf.charAt(i)));
    }

    private Boolean hasAllDigitsEqual(String cpf) {
        return cpf.chars().distinct().count() == 1;
    }

    private Boolean isValidLength(String cpf) {
        return cpf.length() == 11;
    }

    private String cleanCpf(String cpf) {
        return cpf.replace(".", "")
                .replace(".", "")
                .replace("-", "")
                .replace(" ", "");
    }
}
