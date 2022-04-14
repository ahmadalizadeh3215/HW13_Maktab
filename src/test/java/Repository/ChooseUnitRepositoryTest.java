package Repository;

import Entity.*;
import Util.SessionFactorySingleton;
import org.checkerframework.checker.units.qual.C;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChooseUnitRepositoryTest {
    private AllCourseRepository allCourseRepository= new AllCourseRepository();
    private AllCourse allCourse;
    private AllCourse allCourse1;
    private CourseRepository courseRepository;
    private StudentRepository studentRepository= new StudentRepository();
    private ProfessorRepository professorRepository= new ProfessorRepository();
    private ChooseUnitRepository chooseUnitRepository= new ChooseUnitRepository();
    private ChooseUnit chooseUnit = new ChooseUnit();
    private Professor professor;
    private Course course;
    private Course course1;
    private Student student;

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
        ChooseUnitRepository chooseUnitRepository= new ChooseUnitRepository();
        professor = new Professor(2111111121,"javad","zakeri"
                ,"12345", 1000000, StatusEmployee.tuition);
        professorRepository.save(professor);
        course=new Course("dini",2);
        courseRepository.save(course);
        course1=new Course("tarikh",2);
        courseRepository.save(course);
        courseRepository.save(course1);
        student= new Student(1232312234,"ali","alizadeh","123");
        studentRepository.save(student);
        allCourse= new AllCourse(1,2022,course1,professor);
        allCourseRepository.save(allCourse);
        chooseUnit= new ChooseUnit(2022,1,student,allCourse);
        chooseUnitRepository.save(chooseUnit);



    }
    @Test
    public void saveTest() {
        //arrange
        chooseUnit= new ChooseUnit(2022,1,student,allCourse);

        //act
        chooseUnitRepository.save(chooseUnit);
        //assert
        Student student1=studentRepository.findById(Student.class,student.getNationalcode());
        ChooseUnit chooseUnit1= chooseUnitRepository.findById(ChooseUnit.class, chooseUnit.getId());

        assertNotNull(chooseUnit1);
        assertEquals(chooseUnit.getYear(),chooseUnit1.getYear());
        assertEquals(2022, chooseUnit1.getYear());
    }

    @Test
    public void updateTest() {
        //arrange
        chooseUnit= new ChooseUnit(2022,1,student,allCourse);
        chooseUnitRepository.save(chooseUnit);

        //act
     chooseUnit.setTerm(2);
       chooseUnitRepository.update(chooseUnit);

        //assert
ChooseUnit chooseUnit1=chooseUnitRepository.findById(ChooseUnit.class, chooseUnit.getTerm());


        assertNotNull(chooseUnit1);
        assertEquals(2, chooseUnit1.getTerm());
    }

    @Test
    public void deleteTest() {
        //arrange
        chooseUnit= new ChooseUnit(2022,1,student,allCourse);
        chooseUnitRepository.save(chooseUnit);

        //act

       chooseUnitRepository.delete(chooseUnit);
        //assert
ChooseUnit chooseUnitdelete=chooseUnitRepository.findById(ChooseUnit.class, chooseUnit.getId());
       
        assertNull(chooseUnitdelete);
    }

    @Test
    public void findByIdTest() {
        //arrange
        chooseUnit = new ChooseUnit(2022,1,student,allCourse);

        //act
        chooseUnitRepository.save(chooseUnit);
        ChooseUnit chooseUnitfindid = chooseUnitRepository.findById(ChooseUnit.class,chooseUnit.getId());
        //assert
        List<ChooseUnit> chooseUnitList = chooseUnitRepository.findAll(ChooseUnit.class);
        assertAll(
                () -> assertEquals(chooseUnitList.get(0).getId(), chooseUnitfindid.getId()),
                () -> assertEquals(chooseUnitList.get(0).getTerm(), chooseUnitfindid.getTerm()),
                () -> assertEquals(chooseUnitList.get(0).getStudent().getFirstname(), chooseUnitfindid.getStudent().getFirstname())
        );
    }

    @Test
    public void findAllTest() {
        //arrange

        chooseUnit = new ChooseUnit(2022,1,student,allCourse);



        //act
        chooseUnitRepository.save(chooseUnit);
        //assert
        assertEquals(1,chooseUnitRepository.findAll(ChooseUnit.class).size());
    }

    @AfterEach
    public void cleanUp() {
        String[] strings = new String[]{
                "delete from chooseunit",
                "delete from professor",
                "delete from course",
                "delete from student"
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