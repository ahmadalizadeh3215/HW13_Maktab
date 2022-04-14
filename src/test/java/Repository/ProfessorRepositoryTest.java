package Repository;


import Entity.Professor;
import Entity.StatusEmployee;
import Util.SessionFactorySingleton;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProfessorRepositoryTest {

    private ProfessorRepository professorRepository;
    private Professor professor;
    private Professor professor1;
    private SessionFactory sessionFactory;

    @BeforeAll
    public static void beforeAll() {
    }

    @BeforeEach
    public void beforeEach() {
       professorRepository=new ProfessorRepository();
        sessionFactory = SessionFactorySingleton.getInstance();


    }

    @Test
    public void saveTest() {
        //arrange
        professor =new Professor(1111122221,"alis","aalis","1234",350000, StatusEmployee.scienceCommittee);
        //act
       professorRepository.save(professor);

        //assert

        Professor professor2=professorRepository.findById(Professor.class,professor.getNationalcode());

        assertNotNull(professor2);
        assertEquals(professor.getFirstname(), professor2.getFirstname());
        assertEquals(350000, professor2.getSalary());
    }

    @Test
    public void updateTest() {
        //arrange
        professor =new Professor(1111122222,"alis","aalis","1234",350000, StatusEmployee.scienceCommittee);

        professorRepository.save(professor);

        //act
      professor.setPassword("qawse");
      professorRepository.update(professor);

        //assert
        Professor professor2=professorRepository.findById(Professor.class,professor.getNationalcode());

        assertNotNull(professor2);
        assertEquals("qawse", professor2.getPassword());
    }

    @Test
    public void deleteTest() {
        //arrange
        professor =new Professor(1111122223,"alis","aalis","1234",350000, StatusEmployee.scienceCommittee);

        professorRepository.save(professor);

        //act
     professorRepository.delete(professor);

        //assert
        Professor professor2=professorRepository.findById(Professor.class,professor.getNationalcode());
        assertNull(professor2);
    }

    @Test
    public void findByIdTest() {
        //arrange
        professor =new Professor(1111122224,"alis","aalis","1234",350000, StatusEmployee.scienceCommittee);

        professorRepository.save(professor);
        Professor professor2=professorRepository.findById(Professor.class,professor.getNationalcode());
        //assert
        List<Professor> professorList = professorRepository.findAll(Professor.class);
        assertAll(
                () -> assertEquals(professorList.get(0).getNationalcode(), professor2.getNationalcode()),
                () -> assertEquals(professorList.get(0).getSalary(), professor2.getSalary()),
                () -> assertEquals(professorList.get(0).getLastname(), professor2.getLastname())
        );
    }

    @Test
    public void findAllTest() {
        //arrange
        professor =new Professor(1111122225,"alis","aalis","1234",350000, StatusEmployee.scienceCommittee);
        professor1 =new Professor(1111122226,"milad","aalis","1234",30000, StatusEmployee.scienceCommittee);






        //act
        professorRepository.save(professor);
        professorRepository.save(professor1);

        //assert
        assertEquals(2, professorRepository.findAll(Professor.class).size());
    }

    @AfterEach
    public void cleanUp() {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                var query = session.createNativeQuery("delete from professor");
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