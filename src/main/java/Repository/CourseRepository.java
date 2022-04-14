package Repository;

import Entity.Course;

public class CourseRepository implements BaseRepository<Course>{

    public Course findByName(String name){
        var session = sessionFactory.openSession();
        String sql = "select * from course " +
                "where name = :name";
        var queyfindcourse=session.createNativeQuery(sql);

        queyfindcourse.setParameter(1,name);
        return (Course) queyfindcourse.getSingleResult();

    }

}
