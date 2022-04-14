package Repository;

import Entity.Course;
import Entity.Professor;
import Entity.StatusEmployee;
import Util.SessionFactorySingleton;
import org.checkerframework.checker.units.qual.C;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CourseRepositoryTest {
    private CourseRepository courseRepository;
    private Course course;
    private Course course1;
    private SessionFactory sessionFactory;

    @BeforeAll
    public static void beforeAll() {
    }

    @BeforeEach
    public void beforeEach() {
       courseRepository= new CourseRepository();
        sessionFactory = SessionFactorySingleton.getInstance();


    }

    @Test
    public void saveTest() {
        //arrange
       course= new Course("math",3);
        //act
       courseRepository.save(course);

        //assert

       Course course2=courseRepository.findById(Course.class,course.getId());

        assertNotNull(course2);
        assertEquals(course.getNumberUnit(),course2.getNumberUnit());
        assertEquals(3, course2.getNumberUnit());
    }

    @Test
    public void updateTest() {
        //arrange
        course= new Course("math",3);

        courseRepository.save(course);

        //act
       course.setName("programming");
       courseRepository.update(course);

        //assert
        Course course2=courseRepository.findById(Course.class,course.getId());

        assertNotNull(course2);
        assertEquals("programming", course2.getName());
    }

    @Test
    public void deleteTest() {
        //arrange
        course= new Course("math",3);

        courseRepository.save(course);

        //act
       courseRepository.delete(course);

        //assert
        Course course2=courseRepository.findById(Course.class,course.getId());
        assertNull(course2);
    }

    @Test
    public void findByIdTest() {
        //arrange
        course= new Course("math",3);

        courseRepository.save(course);
        Course course2=courseRepository.findById(Course.class,course.getId());
        //assert
        List<Course> courseList = courseRepository.findAll(Course.class);
        assertAll(
                () -> assertEquals(courseList.get(0).getId(),course2.getId()),
                () -> assertEquals(courseList.get(0).getName(), course2.getName()),
                () -> assertEquals(courseList.get(0).getNumberUnit(), course2.getNumberUnit())
        );
    }

    @Test
    public void findAllTest() {
        //arrange
        course= new Course("math",3);
        course1= new Course("math2",4);








        //act
        courseRepository.save(course);
        courseRepository.save(course1);

        //assert
        assertEquals(2, courseRepository.findAll(Course.class).size());
    }

    @AfterEach
    public void cleanUp() {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                var query = session.createNativeQuery("delete from course");
                query.executeUpdate();
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
            }
        }
    }

    @AfterAll
    public static void afterAll() {

    }

}