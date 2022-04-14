package Repository;

import Entity.AllCourse;
import Entity.Course;
import Entity.Professor;
import Entity.StatusEmployee;
import Util.SessionFactorySingleton;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AllCourseRepositoryTest {
    private AllCourseRepository allCourseRepository= new AllCourseRepository();
    private AllCourse allCourse;
    private CourseRepository courseRepository;
    private Professor professor;
    private Course course;
    private SessionFactory sessionFactory;

    @BeforeAll
    public static void beforeAll() {
    }

    @BeforeEach
    public void beforeEach() {
        AllCourseRepository allCourseRepository = new AllCourseRepository();
        sessionFactory = SessionFactorySingleton.getInstance();
        ProfessorRepository professorRepository = new ProfessorRepository();
        CourseRepository courseRepository = new CourseRepository();
        professor = new Professor(2111111121,"javad","zakeri"
                ,"12345", 1000000,StatusEmployee.tuition);
        professorRepository.save(professor);
        course=new Course("dini",2);
        courseRepository.save(course);
       course=new Course("tarikh",2);
        courseRepository.save(course);


    }
    @Test
    public void saveTest() {
        //arrange
       allCourse= new AllCourse(3,2022,course,professor);


        //act
        allCourseRepository.save(allCourse);

        //assert

        AllCourse allcoursefind=allCourseRepository.findById(AllCourse.class,allCourse.getId());

        assertNotNull(allcoursefind);
        assertEquals(allCourse.getTerm(), allcoursefind.getTerm());
        assertEquals(1000000, allcoursefind.getProfessor().getSalary());
    }

    @Test
    public void updateTest() {
        //arrange
        allCourse= new AllCourse(2022,3,course,professor);
        allCourseRepository.save(allCourse);

        //act
        allCourse.setTerm(2);
        allCourseRepository.update(allCourse);

        //assert
        AllCourse allcoursefind=allCourseRepository.findById(AllCourse.class,allCourse.getId());

        assertNotNull(allcoursefind);
        assertEquals(2, allcoursefind.getTerm());
    }

    @Test
    public void deleteTest() {
        //arrange
        allCourse= new AllCourse(2022,3,course,professor);
        allCourseRepository.save(allCourse);

        //act

allCourseRepository.delete(allCourse);
        //assert

        AllCourse coursedelete=allCourseRepository.findById(AllCourse.class,allCourse.getId());
        assertNull(coursedelete);
    }

    @Test
    public void findByIdTest() {
        //arrange
        allCourse= new AllCourse(2022,3,course,professor);

        //act
        allCourseRepository.save(allCourse);
        AllCourse coursedelete=allCourseRepository.findById(AllCourse.class,allCourse.getId());
        //assert
        List<AllCourse> allCourseList = allCourseRepository.findAll(AllCourse.class);
        assertAll(
                () -> assertEquals(allCourseList.get(0).getId(), coursedelete.getId()),
                () -> assertEquals(allCourseList.get(0).getYear(), coursedelete.getYear()),
                () -> assertEquals(allCourseList.get(0).getProfessor().getSalary(), coursedelete.getProfessor().getSalary())
        );
    }

    @Test
    public void findAllTest() {
        //arrange
        allCourse= new AllCourse(2022,3,course,professor);



        //act
        allCourseRepository.save(allCourse);
        //assert
        assertEquals(1, allCourseRepository.findAll(AllCourse.class).size());
    }

    @AfterEach
    public void cleanUp() {
        String[] strings = new String[]{
                "delete from allcourse",
                "delete from professor",
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