package ExceptionManagment;

public class InvalidNationalIdException extends RuntimeException {
    public InvalidNationalIdException() {
    }

    public InvalidNationalIdException(String message) {
        super(message);
    }

    public InvalidNationalIdException(String message, Throwable cause) {
        super(message, cause);
    }


}
