package Repository;

import Entity.AllCourse;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

public class AllCourseRepository implements BaseRepository<AllCourse> {

    public List<AllCourse> findAllTerm(Integer year, Integer term) {
        List<AllCourse> courseArrayList = new ArrayList<>();
        try (var session = sessionFactory.openSession()) {
            String sql = "select * from allcourse " +
                    "where year = :year and term = :term ";
            var query = session.createNativeQuery(sql, AllCourse.class);
            query.setParameter("year", year);
            query.setParameter("term", term);
            query.getResultStream().forEach(courseArrayList::add);
            return courseArrayList;
        }
    }
    public Boolean findCourseByProfessorId(Integer year, Integer term, Integer prfNationalCode, String courseName) {
        try (var session = sessionFactory.openSession()) {
            String sql = "select * from allcourse " +
                    "inner join course c on c.id = allCourse.course_id " +
                    "inner join professor p on p.nationalcode = allCourse.professor_nationalcode " +
                    "where year = :year and term = :term and p.nationalcode = :prfnationalcode  and c.name = :courseName ";
            try {
                var query = session.createNativeQuery(sql, AllCourse.class);
                query.setParameter("year", year);
                query.setParameter("term", term);
                query.setParameter("prfnationalcode", prfNationalCode);
                query.setParameter("courseName", courseName);
                AllCourse result = query.getSingleResult();
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
