package ExceptionManagment;

public class ThisLessonAlreadyPassed extends RuntimeException{
    public ThisLessonAlreadyPassed() {
    }

    public ThisLessonAlreadyPassed(String message) {
        super(message);
    }

    public ThisLessonAlreadyPassed(String message, Throwable cause) {
        super(message, cause);
    }
}
