package ExceptionManagment;

public class InvalidOption extends RuntimeException{
    public InvalidOption() {
    }

    public InvalidOption(String message) {
        super(message);
    }

    public InvalidOption(String message, Throwable cause) {
        super(message, cause);
    }
}
