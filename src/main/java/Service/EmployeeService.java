package Service;

import Entity.Employee;
import Entity.StatusEmployee;
import Entity.Student;
import Repository.EmployeeRepository;

import java.util.List;

public class EmployeeService {
    EmployeeRepository employeeRepository = new EmployeeRepository();

    public Employee save(Employee employee) {
        try {
            return employeeRepository.save(employee);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Employee findById(Integer id) {
        try {
            return employeeRepository.findById(Employee.class, id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void delete(Employee employee) {
        try {
            employeeRepository.delete(employee);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Employee> findAll() {
        try {
            return employeeRepository.findAll(Employee.class);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void update(Employee employee) {
        try {
            employeeRepository.update(employee);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Employee login(Class<Employee> employeeClass, Integer nationalcode, String password) {
        try {
            return employeeRepository.login(Employee.class, nationalcode, password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Employee findByUserName(Class<Employee> employeeClass, String username) {
        try {
            return employeeRepository.findByUserName(Employee.class, username);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void deleteByNationalCode(Class<Employee> employeeClass, String nationalcode) {
        try {
            employeeRepository.DeleteByNationalCode(Employee.class, nationalcode);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Employee findByNationalCode(Class<Employee>employeeClass,String nationalcode){
        try {
            return employeeRepository.findByNationalCode(Employee.class,nationalcode);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public Integer calcSalary(Employee employee,Integer totalOfUnit,Integer year , Integer term){
        if (employee.getStatusEmployee().equals(StatusEmployee.scienceCommittee)){
            return totalOfUnit * 1000000;
        } else
            return totalOfUnit * 1000000 + employee.getSalary();
    }

}