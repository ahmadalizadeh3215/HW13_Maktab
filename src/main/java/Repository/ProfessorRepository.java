package Repository;

import Entity.Professor;

import javax.persistence.NoResultException;

public class ProfessorRepository implements BaseRepository<Professor>{

    public Professor login(Class<Professor> professorClass, Integer nationalCode, String password) {
        Professor professor=null;
        try (var session = sessionFactory.openSession()) {
            String sql = "SELECT * FROM professor WHERE nationalcode = :nationalCode and password = :password";
            var query = session.createNativeQuery(sql, Professor.class);
            query.setParameter("nationalCode", nationalCode);
            query.setParameter("password", password);
            professor = query.getSingleResult();
            return professor;
        }

    }
    public Integer findCourseUnitById(Integer nationalCode, Integer year, Integer term) {
        try (var session = sessionFactory.openSession()) {
            String sql = "select sum(unit) from professor " +
                    "inner join allcourse ac on professor.nationalcode = ac.professor_nationalcode " +
                    "inner join course c on c.id = ac.course_id " +
                    "where nationalcode = :nationalcode and year = :year and term = :term ";
            try {
                var query = session.createNativeQuery(sql);
                query.setParameter("nationalcode", nationalCode);
                query.setParameter("year", year);
                query.setParameter("term", term);
                if (query.getSingleResult() == null) {
                    return null;
                } else
                    return ((Number) query.getSingleResult()).intValue();
            } catch (NoResultException e) {
                return null;
            }
        }
    }
}
