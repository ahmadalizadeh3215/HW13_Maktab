package ExceptionManagment;

public class InvalidGrade extends RuntimeException {
    public InvalidGrade() {
    }

    public InvalidGrade(String message) {
        super(message);
    }

    public InvalidGrade(String message, Throwable cause) {
        super(message, cause);
    }


}
