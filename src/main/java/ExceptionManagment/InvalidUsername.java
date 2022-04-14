package ExceptionManagment;

public class InvalidUsername extends RuntimeException{
    public InvalidUsername() {
    }

    public InvalidUsername(String message) {
        super(message);
    }

    public InvalidUsername(String message, Throwable cause) {
        super(message, cause);
    }


}
