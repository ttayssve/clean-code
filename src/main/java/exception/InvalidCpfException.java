package exception;

public class InvalidCpfException extends RuntimeException {

    private final String message;

    public InvalidCpfException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
