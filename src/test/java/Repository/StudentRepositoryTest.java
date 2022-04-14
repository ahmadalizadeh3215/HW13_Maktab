package Repository;


import Entity.Student;
import Util.SessionFactorySingleton;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentRepositoryTest {
    private StudentRepository studentRepository;
    private Student student;
    private SessionFactory sessionFactory;

    @BeforeAll
    public static void beforeAll() {
    }

    @BeforeEach
    public void beforeEach() {
        studentRepository = new StudentRepository();
        sessionFactory = SessionFactorySingleton.getInstance();


    }

    @Test
    public void saveTest() {
        //arrange
        student =new Student(1112221121,"s1","s","12345");
        //act
        studentRepository.save(student);

        //assert

        Student student2= studentRepository.findById(Student.class,student.getNationalcode());

        assertNotNull(student2);
        assertEquals(student.getFirstname(), student2.getFirstname());
        assertEquals("s1", student2.getFirstname());
    }

    @Test
    public void updateTest() {
        //arrange
        student =new Student(1112221123,"s1","s","12345");
        studentRepository.save(student);

        //act
       student.setPassword("qwer");
        studentRepository.update(student);

        //assert


        Student studentup= studentRepository.findById(Student.class,student.getNationalcode());

        assertNotNull(studentup);
        assertEquals("qwer", studentup.getPassword());
    }

    @Test
    public void deleteTest() {
        //arrange
        student =new Student(1112221125,"s1","s","12345");
        studentRepository.save(student);

        //act
        studentRepository.delete(student);

        //assert
        Student studentdel= studentRepository.findById(Student.class,student.getNationalcode());
        assertNull(studentdel);
    }

    @Test
    public void findByIdTest() {
        //arrange
        student =new Student(1112221129,"s1","s","12345");


        //act
        studentRepository.save(student);
        Student studentfall= studentRepository.findById(Student.class,student.getNationalcode());
        //assert
        List<Student> studentList = studentRepository.findAll(Student.class);
        assertAll(
                () -> assertEquals(studentList.get(0).getNationalcode(), studentfall.getNationalcode()),
                () -> assertEquals(studentList.get(0).getFirstname(), studentfall.getFirstname()),
                () -> assertEquals(studentList.get(0).getLastname(), studentfall.getLastname())
        );
    }

    @Test
    public void findAllTest() {
        //arrange
        student =new Student(1112221127,"s1","s","12345");

        Student student1 = new Student(1112221124, "s2", "s2", "12345");



        //act
        studentRepository.save(student);
        studentRepository.save(student1);


        //assert
        assertEquals(4, studentRepository.findAll(Student.class).size());
    }

    @AfterEach
    public void cleanUp() {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                var query = session.createNativeQuery("delete from student");
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