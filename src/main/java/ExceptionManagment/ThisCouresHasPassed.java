package ExceptionManagment;

public class ThisCouresHasPassed extends RuntimeException{
    public ThisCouresHasPassed() {
    }

    public ThisCouresHasPassed(String message) {
        super(message);
    }

    public ThisCouresHasPassed(String message, Throwable cause) {
        super(message, cause);
    }
}
