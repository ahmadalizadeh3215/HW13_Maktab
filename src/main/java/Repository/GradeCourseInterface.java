package Repository;

import Entity.GradeCourse;
import Util.SessionFactorySingleton;
import org.hibernate.SessionFactory;

import java.util.List;

public interface GradeCourseInterface extends BaseRepository<GradeCourse>{
    public Double calculateAvarage(String nationalcode,Integer year,Integer term);
    public List<GradeCourse> passedLesson(String nationalCode);
    public Boolean checkLessonPassed(String nationalCode, String lessonName);

}
