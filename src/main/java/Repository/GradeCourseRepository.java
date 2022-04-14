package Repository;

import Entity.GradeCourse;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

public class GradeCourseRepository implements BaseRepository<GradeCourse> {
    public static Double calculateAvarage(Integer nationalcode, Integer year, Integer term) {
        try (var session = sessionFactory.openSession()) {
            String sql = "select Avg(grade) from gradecourse " +
                    "where student_nationalcode = :nationalcode and year= :year and term = :term ";
            var query = session.createNativeQuery(sql);
            query.setParameter("nationalcode", nationalcode);
            query.setParameter("term", term);
            query.setParameter("year", year);
            return (Double) query.getSingleResult();

        }
    }

    public List<GradeCourse> passedCourse(Integer nationalCode) {
        List<GradeCourse> gradeCourseList = new ArrayList<>();
        try (var session = sessionFactory.openSession()) {
            String sql = "select * from gradecourse " +
                    "where student_nationalcode = :nationalcode and grade >= 10 ";
            var query = session.createNativeQuery(sql, GradeCourse.class);
            query.setParameter("nationalcode", nationalCode);
            query.getResultStream().forEach(gradeCourseList::add);
            return gradeCourseList;
        }
    }

    public Boolean checkLessonPassed(Integer nationalCode, String coursename) {
        try (var session = sessionFactory.openSession()) {
            String sql = "select gs.* from gradecourse gs\n" +
                    "inner join student s on s.nationalcode = gs.student_nationalcode\n" +
                    "inner join course c on c.id = gs.course_id\n" +
                    "where s.nationalcode= :nationalcode and c.name= :coursename";
            try {
                var query = session.createNativeQuery(sql, GradeCourse.class);
                query.setParameter("nationalcode", nationalCode);
                query.setParameter("coursename", coursename);
                GradeCourse result = query.getSingleResult();
                if (result == null)
                    return false;
                else
                    return true;
            } catch (NoResultException e) {
                return false;
            }
        }
    }
}