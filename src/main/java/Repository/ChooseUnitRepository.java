package Repository;

import Entity.ChooseUnit;
import Entity.Student;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

public class ChooseUnitRepository implements BaseRepository<ChooseUnit>{
    public Integer calcTotalUnit(Integer nationalCode, Integer year, Integer term) {
        try (var session = sessionFactory.openSession()) {
            String sql = "select Sum(numberUnit) from chooseunit " +
                    "INNER JOIN allcourse ac on ac.id = chooseunit.allcourse_id " +
                    "INNER JOIN course c on c.id = ac.course_id " +
                    "where student_nationalcode = :nationalcode and chooseunit.year = :year and chooseunit.term = :term";
            var query = session.createNativeQuery(sql);
            query.setParameter("nationalcode", nationalCode);
            query.setParameter("year", year);
            query.setParameter("term", term);
            if (query.getSingleResult() == null) {
                return null;
            } else
                return ((Number) query.getSingleResult()).intValue();
        }
    }


    public Boolean checkCourseGiven(Integer nationalCode, String courseName, Integer year, Integer term) {
        try (var session = sessionFactory.openSession()) {
            String sql = "select  c.* from chooseunit c " +
                    "inner join student s on s.nationalcode = c.student_nationalcode " +
                    "inner join allcourse ac on ac.id = c.allcourse_id " +
                    "inner join course c1 on c1.id = ac.course_id " +
                    "where s.nationalcode = ?1 and c1.name = ?2 and c.year = ?3 and c.term = ?4 ";

          try {
                var query = session.createNativeQuery(sql, ChooseUnit.class);
                query.setParameter(1, nationalCode);
                query.setParameter(2, courseName);
                query.setParameter(3, year);
                query.setParameter(4, term);
                ChooseUnit result = query.getSingleResult();
                if (result == null)
                    return false;
                else
                    return true;
            } catch (NoResultException e) {
                return false;
            }
        }
    }
    public List<ChooseUnit> findAllByLessonNameAndProfessorId(Integer prfNationalCode, String courseName, Integer year, Integer term) {
        List<ChooseUnit> chooseUnitList = new ArrayList<>();
        try (var session = sessionFactory.openSession()) {
            String sql = "select c.* from chooseunit c " +
                    "inner join allcourse ac on ac.id = c.allcourse_id " +
                    "inner join course c1 on c1.id = c1.course_id " +
                    "where professor_nationalcode = :prfnationalcode and" +
                    " c1.name = :courseName and c.year = :year and c.term = :term ";
            var query = session.createNativeQuery(sql, ChooseUnit.class);
            query.setParameter("prfnationalcode", prfNationalCode);
            query.setParameter("courseName", courseName);
            query.setParameter("year", year);
            query.setParameter("term", term);
            query.getResultStream().forEach(chooseUnitList::add);
            return chooseUnitList;
        }
    }
    public List<ChooseUnit> findByNationalCodeStuCourse(Integer nationalCode){
        List<ChooseUnit> chooseUnitList = new ArrayList<>();
        try (var session = sessionFactory.openSession()){
            String sql="select c.*  from chooseunit c\n" +
                    " inner join student s on s.nationalcode = c.student_nationalcode\n" +
                    "inner join course c1 on c1.id=allcourse_id\n" +
                    "where s.nationalcode= :nationalcode";
            var query = session.createNativeQuery(sql, ChooseUnit.class);
            query.setParameter("nationalcode", nationalCode);
            query.getResultStream().forEach(chooseUnitList::add);
            return chooseUnitList;

        }

    }
}
