package ExceptionManagment;

public class InvalidUsernameOrpassword extends RuntimeException {
    public InvalidUsernameOrpassword() {
    }

    public InvalidUsernameOrpassword(String message) {
        super(message);
    }

    public InvalidUsernameOrpassword(String message, Throwable cause) {
        super(message, cause);
    }
}
