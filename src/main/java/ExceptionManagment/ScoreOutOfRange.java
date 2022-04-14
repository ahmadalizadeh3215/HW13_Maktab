package ExceptionManagment;

public class ScoreOutOfRange extends RuntimeException{
    public ScoreOutOfRange() {
    }

    public ScoreOutOfRange(String message) {
        super(message);
    }

    public ScoreOutOfRange(String message, Throwable cause) {
        super(message, cause);
    }
}
