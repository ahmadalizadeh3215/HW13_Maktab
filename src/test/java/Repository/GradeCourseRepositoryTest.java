package Repository;

import Entity.Course;
import Entity.GradeCourse;
import Entity.Student;
import Util.SessionFactorySingleton;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GradeCourseRepositoryTest {

    private static SessionFactory sessionFactory;
    private GradeCourse gradeCourse;
    private final GradeCourseRepository gradeCourseRepository=new GradeCourseRepository();
    private final StudentRepository studentRepository= new StudentRepository();
    private final CourseRepository courseRepository= new CourseRepository();
    private Student student;
    private Course course;


    @BeforeAll
    public static void beforeAll() {

    }

    @BeforeEach
    public void beforeEach() {
        sessionFactory = SessionFactorySingleton.getInstance();





    }

    @Test
    public void saveTest() {

        //arrange
        student= new Student(1222222112,"ali","alizadeh","123");
        studentRepository.save(student);
        course=new Course("dini",3);
        courseRepository.save(course);
        gradeCourse= new GradeCourse(2022,1,student,course,19);

        //act
        gradeCourseRepository.save(gradeCourse);

        //assert
        GradeCourse gradeCoursefind = gradeCourseRepository.findById(GradeCourse.class, gradeCourse.getId());
        assertNotNull(gradeCoursefind);
        assertEquals(gradeCoursefind.getTerm(), gradeCoursefind.getTerm());
        assertEquals(19, gradeCoursefind.getGrade());
    }

    @Test
    public void updateTest() {
        //arrange
        student= new Student(1222222113,"ali","alizadeh","123");
        studentRepository.save(student);
        course=new Course("dini",3);
        courseRepository.save(course);
        gradeCourse= new GradeCourse(2022,1,student,course,18);
        gradeCourseRepository.save(gradeCourse);

        //act
      gradeCourse.setGrade(18);
      gradeCourseRepository.update(gradeCourse);

        //assert
        GradeCourse grupdate=gradeCourseRepository.findById(GradeCourse.class,gradeCourse.getId());

        assertNotNull(grupdate);
        assertEquals(18, grupdate.getGrade());
    }

    @Test
    public void deleteTest() {
        //arrange
        student= new Student(1222222114,"ali","alizadeh","123");
        studentRepository.save(student);
        course=new Course("dini",3);
        courseRepository.save(course);
        gradeCourse= new GradeCourse(2022,1,student,course,19);
        gradeCourseRepository.save(gradeCourse);

        //act

gradeCourseRepository.delete(gradeCourse);
        //assert

        GradeCourse grupdate=gradeCourseRepository.findById(GradeCourse.class,gradeCourse.getId());

        assertNull(grupdate);
    }

    @Test
    public void findByIdTest() {
        //arrange
        student= new Student(1222222111,"ali","alizadeh","123");
        course=new Course("dini",3);
        gradeCourse= new GradeCourse(2022,1,student,course,19);


        //act
        studentRepository.save(student);
        courseRepository.save(course);
        gradeCourseRepository.save(gradeCourse);
        GradeCourse grupdate=gradeCourseRepository.findById(GradeCourse.class,gradeCourse.getId());
        //assert
        List<GradeCourse> gradeCourseList = gradeCourseRepository.findAll(GradeCourse.class);
        assertAll(
                () -> assertEquals(gradeCourseList.get(0).getId(), grupdate.getId()),
                () -> assertEquals(gradeCourseList.get(0).getStudent().getNationalcode(), grupdate.getStudent().getNationalcode()),
                () -> assertEquals(gradeCourseList.get(0).getGrade(), grupdate.getGrade())
        );
    }


    @Test
    public void findAllTest() {
        //arrange
        student= new Student(1222222119,"ali","alizadeh","123");
        studentRepository.save(student);
        course=new Course("dini",3);
        Course course1 = new Course("tarikh", 2);
        courseRepository.save(course1);
        courseRepository.save(course);
        gradeCourse= new GradeCourse(2022,1,student,course,19);

        GradeCourse gradeCourse1 = new GradeCourse(2022, 1, student, course, 19);



        //act
        gradeCourseRepository.save(gradeCourse1);
        gradeCourseRepository.save(gradeCourse);

        //assert
        assertEquals(4, gradeCourseRepository.findAll(GradeCourse.class).size());
    }

    @AfterEach
    public void cleanUp() {
        String[] strings = new String[]{
                "delete from allcourse",
                "delete from student",
                "delete from course"
        };
        for (String s : strings) {
            try (var session = sessionFactory.openSession()) {
                var transaction = session.beginTransaction();
                try {
                    var query = session.createNativeQuery(s);
                    query.executeUpdate();
                    transaction.commit();
                } catch (Exception e) {
                    transaction.rollback();
                }
            }
        }
    }

    @AfterAll
    public static void afterAll() {

    }

}