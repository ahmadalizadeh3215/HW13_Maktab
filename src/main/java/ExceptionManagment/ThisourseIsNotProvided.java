package ExceptionManagment;

public class ThisourseIsNotProvided extends RuntimeException{
    public ThisourseIsNotProvided() {
    }

    public ThisourseIsNotProvided(String message) {
        super(message);
    }

    public ThisourseIsNotProvided(String message, Throwable cause) {
        super(message, cause);
    }
}
