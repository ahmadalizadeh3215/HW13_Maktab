package Repository;

import Entity.Employee;
import Entity.StatusEmployee;
import Util.SessionFactorySingleton;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeRepositoryTest {
    private EmployeeRepository employeeRepository;
    private Employee employee;
    private SessionFactory sessionFactory;

    @BeforeAll
    public static void beforeAll() {
    }

    @BeforeEach
    public void beforeEach() {
        employeeRepository = new EmployeeRepository();
        sessionFactory = SessionFactorySingleton.getInstance();


    }

    @Test
    public void saveTest() {
        //arrange
        employee =new Employee(1111122221,"alis","aalis","1234",100000, StatusEmployee.TrainingEmployee);
        //act
        employeeRepository.save(employee);

        //assert
        Employee loadedEmp = employeeRepository.findById(Employee.class,employee.getNationalcode());

        assertNotNull(loadedEmp);
        assertEquals(employee.getFirstname(), loadedEmp.getFirstname());
        assertEquals(100000, loadedEmp.getSalary());
    }

    @Test
    public void updateTest() {
        //arrange
        employee = new Employee(1111122221,"alis","aalis","1234"
                ,100000, StatusEmployee.TrainingEmployee);
        employeeRepository.save(employee);

        //act
        employee.setSalary(15000);
        employeeRepository.update(employee);

        //assert
        Employee loadedEmp = employeeRepository.findById(Employee.class,employee.getNationalcode());

        assertNotNull(loadedEmp);
        assertEquals(15000, loadedEmp.getSalary());
    }

    @Test
    public void deleteTest() {
        //arrange
        employee = new Employee(1111122221,"alis","aalis","1234"
                ,100000, StatusEmployee.TrainingEmployee);
        employeeRepository.save(employee);

        //act
        employeeRepository.delete(employee);

        //assert
        Employee loadedEmp = employeeRepository.findById(Employee.class,employee.getNationalcode());
        assertNull(loadedEmp);
    }

    @Test
    public void findByIdTest() {
        //arrange
        employee =new Employee(1111122221,"alis","aalis","1234",100000, StatusEmployee.TrainingEmployee);

        //act
        employeeRepository.save(employee);
        Employee loadedEmp = employeeRepository.findById(Employee.class,employee.getNationalcode());
        //assert
        List<Employee> employeeList = employeeRepository.findAll(Employee.class);
        assertAll(
                () -> assertEquals(employeeList.get(0).getNationalcode(), loadedEmp.getNationalcode()),
                () -> assertEquals(employeeList.get(0).getSalary(), loadedEmp.getSalary()),
                () -> assertEquals(employeeList.get(0).getLastname(), loadedEmp.getLastname())
        );
    }

    @Test
    public void findAllTest() {
        //arrange
        employee = new Employee(1111122221,"alis","aalis","1234"
                ,100000, StatusEmployee.TrainingEmployee);
        Employee employee1 = new Employee(1111122222,"ali","aal","1234"
                ,100000, StatusEmployee.TrainingEmployee);


        //act
        employeeRepository.save(employee);
        employeeRepository.save(employee1);

        //assert
        assertEquals(2, employeeRepository.findAll(Employee.class).size());
    }

    @AfterEach
    public void cleanUp() {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                var query = session.createNativeQuery("delete from employee");
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