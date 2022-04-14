package ExceptionManagment;

public class ThisLessonHasAlreadyBeenTaken extends RuntimeException{
    public ThisLessonHasAlreadyBeenTaken() {
    }

    @Override
    public String toString() {return "This Lesson Has Already Been Taken";}
}
