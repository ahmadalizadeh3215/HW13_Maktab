package Util;

import Entity.*;
import Service.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FakeDB {

    private EmployeeService employeeService = new EmployeeService();
    private StudentService studentService = new StudentService();
    private ProfessorService professorService = new ProfessorService();
    private CourseService courseService = new CourseService();
    private AllCourseService allCourseService = new AllCourseService();
    private ChooseUnitService chooseUnitService = new ChooseUnitService();
    private GradeCourseService gradeCourseService = new GradeCourseService();


    public void fillDB() {
        List<Employee> employeeList = new ArrayList<>();
        Employee[] employees = {
                new Employee(1234567890,"ali","alizade","12345"
                        ,1000000,StatusEmployee.TrainingEmployee),
                new Employee(190909090,"ali1","alizadeh1"
                        ,"ali1",1000000, StatusEmployee.TrainingEmployee),

                new Employee(1009090908,"ali3","alizadeh3"
                        ,"ali3",1000000, StatusEmployee.TrainingEmployee)

        };
        Collections.addAll(employeeList, employees);
        employeeList.forEach(employeeService::save);

        List<Professor> professorList = new ArrayList<>();
        Professor[] professors = {
                new Professor(2111111101,"mohsen","mohseni","12345",2500000,StatusEmployee.tuition),
                new Professor(2111111102,"mostafa","mohammadi","12345",3500000,StatusEmployee.scienceCommittee),
                new Professor(2111111103,"mobin","mokhtari","12345",500000,StatusEmployee.tuition),
                new Professor(2111111105,"mojib","mosavi","12345",5000000,StatusEmployee.scienceCommittee)
        };
        Collections.addAll(professorList, professors);
        professorList.forEach(professorService::save);
        List<Student> studentList = new ArrayList<>();
        Student[] students = {

               new Student(1234567800,"ali","alizade","12345"),
                new Student(1234567412,"sajad","sarani","12345"),
                new Student(1008979870,"abas","ramezani","1234")

        };
        Collections.addAll(studentList, students);
        studentList.forEach(studentService::save);

        List<Course> lessonList = new ArrayList<>();
        Course[] courses = {
                new Course("math1", 3),
                new Course("shimi", 3),
                new Course("dini", 2),
                new Course("olum", 3),
                new Course("zist", 3),
                new Course("math2", 3),
                new Course("network", 3),
                new Course("Java SE", 3),
                new Course("programming", 3)
        };
        Collections.addAll(lessonList, courses);
        lessonList.forEach(courseService::save);

        List<AllCourse> courseArrayList = new ArrayList<>();
        AllCourse[] allCourses = {
                new AllCourse(1,2022,courses[0], professors[0] ),
                new AllCourse(1,2022,courses[1], professors[1] ),
                new AllCourse(2,2022,courses[2], professors[2] ),
                new AllCourse(1,2022,courses[3], professors[1]),
                new AllCourse(2,2022,courses[4], professors[1] ),
                new AllCourse(1,2022,courses[5], professors[0] ),
                new AllCourse(1,2022,courses[6], professors[3] ),
                new AllCourse(1,2022,courses[7], professors[3] ),
                new AllCourse(1,2022,courses[8], professors[3] )
        };
        Collections.addAll(courseArrayList,allCourses);
        courseArrayList .forEach(allCourseService::save);


        List<ChooseUnit> chooseUnitList = new ArrayList<>();
        ChooseUnit[] chooseUnits = {

                new ChooseUnit(2022,1,students[0],allCourses[0]),
                new ChooseUnit(2022,1,students[0],allCourses[1]),
                new ChooseUnit(2022,2,students[1],allCourses[1]),
                new ChooseUnit(2022,1,students[1],allCourses[3]),
                new ChooseUnit(2022,1,students[0],allCourses[2]),


        };
        Collections.addAll(chooseUnitList,chooseUnits);
        chooseUnitList.forEach(chooseUnitService::save);

        List<GradeCourse> gradeCourseList = new ArrayList<>();
        GradeCourse[] gradeCourses = {
                new GradeCourse(2021,3,students[0],courses[0],19 ),
                new GradeCourse(2021,3,students[0],courses[2],16 ),
                new GradeCourse(2021,3,students[1],courses[3],14 ),
                new GradeCourse(2021,3,students[1],courses[5],10 )
        };
        Collections.addAll(gradeCourseList,gradeCourses);
        gradeCourseList.forEach(gradeCourseService::save);
    }

}
