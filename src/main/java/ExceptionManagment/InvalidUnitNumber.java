package ExceptionManagment;

public class InvalidUnitNumber extends RuntimeException{
    public InvalidUnitNumber() {
    }

    public InvalidUnitNumber(String message) {
        super(message);
    }

    public InvalidUnitNumber(String message, Throwable cause) {
        super(message, cause);
    }


}
