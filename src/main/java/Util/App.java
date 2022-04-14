package Util;

import Entity.*;
import ExceptionManagment.*;
import Service.*;

import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    private static Integer selectOption, nationalCode;
    private static String typeOfUser, password;
    private static final EmployeeService employeeService = new EmployeeService();
    private static final StudentService studentService = new StudentService();
    private static final CourseService courseService = new CourseService();
    private static final ChooseUnitService chooseUnitService = new ChooseUnitService();
    private static final AllCourseService allCourseService = new AllCourseService();
    private static final GradeCourseService gradeCourseService = new GradeCourseService();
    private static final ProfessorService professorService = new ProfessorService();
    private static Professor professor;
    private static Employee employee;
    private static Student student;
    private static Course course;
    private static AllCourse allCourse;
    private static GradeCourse gradeCourse;
    private static ChooseUnit chooseUnit;
    private static final LocalDateTime now = LocalDateTime.now();
    private static Scanner input = new Scanner(System.in);
    private static int command;

    public static int homeMenu() {
        try {
            System.out.println("1.Employee");
            System.out.println("2.Student");
            System.out.println("3.Professor");
            System.out.println("4.Exit");
            System.out.println("Please Choose Your Commands :");
            command = getNumber();
            input.nextLine();
            switch (command) {
                case 1:
                    return 1;
                case 2:
                    return 2;
                case 3:
                    return 3;
                case 4:
                    return 4;
                default:
                    return 0;

            }
        } catch (InputMismatchException e) {
            System.out.println(e);
            input.nextLine();
            return 5;
        }
    }

    public static int getNumber() {
        while (true) {
            try {

                return input.nextInt();

            } catch (InputMismatchException e) {
                System.out.println("Invalid value  ");
                System.out.println("Enter correct value : ");
                input.nextLine();
            }
        }

    }

    public void loginMenuEmployee() {
        try {
            System.out.println("Menu Login  Employee");
            System.out.print("Enter nationalCode : ");
            nationalCode = getNumber();
            input.nextLine();
            System.out.print("Enter passWord : ");
            password = input.nextLine();
            if ((nationalCode == 1 && password.equals("a"))) {
                menuEmployee();
            } else if ((employeeService.login(Employee.class, nationalCode, password)) != null) {
                employee = employeeService.login(Employee.class, nationalCode, password);
                menuEmployee();
            } else throw new InvalidUsernameOrpassword();
        } catch (InvalidUsernameOrpassword e) {
            System.out.println(e);
            homeMenu();
        }

    }

    public static void menuEmployee() {
        try {
            System.out.println("Menu Employee");
            System.out.println("1. Manage Student ");
            System.out.println("2.Manage Employee");
            System.out.println("3.Manage Course");
            System.out.println("4.Show salary ");
            System.out.println("5.Previous Menu  ");
            System.out.println("6.Exit ");
            System.out.println("choose enter of above Number :");
            int number = getNumber();
            if (number < 7) {
                switch (number) {
                    case 1:
                        menuManageStudent();
                        break;
                    case 2:
                        menuManagerProfessor();
                        break;
                    case 3:
                        menuManageCourse();
                        break;
                    case 4:
                        salary(employee.getStatusEmployee());
                        menuEmployee();
                        break;
                    case 5:
                        homeMenu();
                        break;
                    case 6:
                        System.out.println("God Luck !!!");
                        System.exit(0);
                        break;
                }

            } else {
                System.out.println(" the number is out of range ");
                System.out.println("-------------------------------");
            }
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void salary(StatusEmployee statusEmployee) {
        try {
            if (statusEmployee.equals(StatusEmployee.TrainingEmployee)) {
                System.out.println("your salary is :" + employee.getSalary());
            } else if (statusEmployee.equals(StatusEmployee.tuition)) {
                System.out.println("your salary is :" + professor.getSalary());
            } else if (statusEmployee.equals(StatusEmployee.scienceCommittee)) {
                System.out.println("your salary is :" + professor.getSalary());
            } else {
                throw new InvalidOption();
            }
        } catch (InvalidOption e) {
            System.out.println(e);
            menuEmployee();

        }
    }

    public static void menuManagerProfessor() {
        try {
            System.out.println("1.add new professor");
            System.out.println("2.update informational professor");
            System.out.println("3.remove professor");
            System.out.println("4.show all professor");
            System.out.println("5.back to main menu");
            System.out.print("please select one option:");
            selectOption = input.nextInt();
            if (selectOption == 1) {
                menuRegiseterProfessor();
            } else if (selectOption == 2) {
                System.out.print("professor national code:");
                String nationalCode = input.next();
                checkNationalCode(nationalCode);
                input.nextLine();
                System.out.print("new password:");
                String password = input.next();
                input.nextLine();
                System.out.print("new salary:");
                Integer salary = input.nextInt();
                Professor prfLoaded = professorService.findById(Integer.valueOf(nationalCode));
                System.out.println("new type of employment");
                System.out.println("1.scienceCommittee");
                System.out.println("2.tuition");
                System.out.print("please select one option:");
                Integer typeOfEmployment = input.nextInt();
                if (typeOfEmployment == 1) {
                    prfLoaded.setStatusEmployee(StatusEmployee.scienceCommittee);
                } else if (typeOfEmployment == 2) {
                    prfLoaded.setStatusEmployee(StatusEmployee.tuition);
                } else throw new InvalidOption();
                prfLoaded.setPassword(password);
                prfLoaded.setSalary(salary);
                professorService.update(prfLoaded);
                System.out.println("update success");
                menuManagerProfessor();
            } else if (selectOption == 3) {
                System.out.print("professor national code:");
                String nationalCode = input.next();
                checkNationalCode(nationalCode);
                professor = professorService.findById(Integer.valueOf(nationalCode));
                professorService.delete(professor);
                System.out.println("remove success!");
                menuManagerProfessor();
            } else if (selectOption == 4) {
                professorService.findAll().forEach(System.out::println);
                menuManagerProfessor();
            } else if (selectOption == 5) {
                homeMenu();
            } else throw new InvalidOption();
        } catch (InvalidNationalIdException | InvalidOption | RecordDoesNotExist e) {
            System.out.println(e);
            menuManagerProfessor();
        } catch (NullPointerException e) {
            System.out.println("database error!");
            menuManagerProfessor();
        }
    }

    public static void menuRegiseterProfessor() {
        input.nextLine();
        Professor professor = new Professor();
        try {
            System.out.println("Menu Register professor ");
            System.out.println("---------------------------------");
            System.out.println("Enter yor firstname : ");
            professor.setFirstname(input.nextLine());
            System.out.println("Enter your lastname : ");
            professor.setLastname(input.nextLine());
            System.out.println("Enter your NationalCode : ");
            String nationalCode = input.next();
            checkNationalCode(nationalCode);
            input.nextLine();
            professor.setNationalcode(Integer.valueOf(nationalCode));
            System.out.println("Enter your password : ");
            professor.setPassword(input.nextLine());
            System.out.println("choose your Status : ");
            System.out.println("1.TrainingEmployee     2.tuition     3.scienceCommittee");
            int status = getNumber();
            if (status == 1) {
                professor.setStatusEmployee(StatusEmployee.TrainingEmployee);
                professor.setSalary(1000000);
            } else if (status == 2) {
                professor.setStatusEmployee(StatusEmployee.tuition);
            } else if (status == 3) {
                professor.setStatusEmployee(StatusEmployee.scienceCommittee);
                professor.setSalary(1000000);

            } else {
                throw new InvalidOption();
            }
            professorService.save(professor);
            System.out.println("saved!!!");
            menuManagerProfessor();
        } catch (InvalidOption e) {
            System.out.println(e);
            menuRegiseterProfessor();
        }
    }

    public static void menuManageStudent() {
        try {
            System.out.println("1.add new student");
            System.out.println("2.update informational student");
            System.out.println("3.remove student");
            System.out.println("4.show all students");
            System.out.println("5.back to main menu");
            System.out.print("please select one option:");
            selectOption = input.nextInt();
            if (selectOption == 1) {
                menuRegisterStudent();
            } else if (selectOption == 2) {
                System.out.print("student national code:");
                String nationalCode = input.next();
                checkNationalCode(nationalCode);
                input.nextLine();
                System.out.print("new password:");
                String password = input.next();
                Student stdLoaded = studentService.findById(Integer.valueOf(nationalCode));
                stdLoaded.setPassword(password);
                studentService.update(stdLoaded);
                System.out.println("update success");
                menuManageStudent();
            } else if (selectOption == 3) {
                System.out.print("student national code:");
                String nationalCode = input.next();
                checkNationalCode(nationalCode);
                Integer de = studentService.deleteById(Student.class, Integer.valueOf(nationalCode));
                System.out.println("remove success!");
                menuManageStudent();
            } else if (selectOption == 4) {
                studentService.findAll().forEach(System.out::println);
                menuManageStudent();
            } else if (selectOption == 5) {
                menuEmployee();
            } else throw new InvalidOption();
        } catch (InvalidNationalIdException | InvalidOption | RecordDoesNotExist e) {
            System.out.println(e);
            menuEmployee();
        } catch (NullPointerException e) {
            System.out.println("database error!");
            menuEmployee();
        }
    }


    private static void menuRegisterStudent() {
        input.nextLine();
        Student student = new Student();
        try {
            System.out.println("---------------------------------");
            System.out.println("Menu Register Student ");

            System.out.println("Enter  firstname : ");
            student.setFirstname(input.nextLine());
            System.out.println("Enter  lastname : ");
            student.setLastname(input.nextLine());
            System.out.println("Enter  NationalCode : ");
            String nationalcode = input.nextLine();
            checkNationalCode(nationalcode);
            student.setNationalcode(Integer.valueOf(nationalcode));
            System.out.println("Enter  password : ");
            student.setPassword(input.nextLine());
            studentService.save(student);
            System.out.println("saved!!!");
            menuManageStudent();
            input.nextLine();
        } catch (InvalidOption e) {
            System.out.println(e);
        }

    }

    public void menuLoginStudent() {
        try {
            System.out.println("Login  Student");
            System.out.print("Enter nationlCode : ");
            nationalCode = getNumber();
            input.nextLine();
            System.out.print("Enter passWord : ");
            password = input.nextLine();
            if ((studentService.login(Student.class, nationalCode, password)) != null) {
                student = studentService.login(Student.class, nationalCode, password);
                menuUserStudent();
            } else {
                throw new InvalidUsernameOrpassword();
            }
        } catch (InvalidUsernameOrpassword e) {
            System.out.println(e);
            homeMenu();

        }
    }

    private void menuUserStudent() {
        try {
            System.out.println("User " + student.getFirstname() + " " + student.getLastname());
            System.out.println("||1.Show Info    ||2.Show All Course     ||3.Choose Unit    ||4.Show passed course" + "   ||5.show Choose Unit  ||6.previus menu     ||7.Exit");
            System.out.println(" Choose Number :");
            int number = getNumber();
            switch (number) {
                case 1:
                    System.out.println(student);
                    menuUserStudent();
                    break;
                case 2:
                    allCourseService.findAllTerm(now.getYear(), calcTerm()).forEach(System.out::println);
                    menuUserStudent();
                    break;
                case 3:
                    allCourseService.findAllTerm(now.getYear(), calcTerm()).forEach(System.out::println);
                    System.out.print("please input course id:");
                    Integer courseId = getNumber();
                    if (allCourseService.findById(courseId) != null) {
                        allCourse = allCourseService.findById(courseId);
                        chooseUnit = new ChooseUnit(now.getYear(), calcTerm(), student, allCourse);
                        chooseUnitService.save(chooseUnit);
                        System.out.println("inserted success");
                    } else {
                        throw new ThisourseIsNotProvided();
                    }
                    menuUserStudent();
                    break;
                case 4:
                    gradeCourseService.passedCourse(student.getNationalcode()).forEach(System.out::println);
                    menuUserStudent();
                    break;
                case 5:
                    chooseUnitService.findByNationalCodeStuCourse(student.getNationalcode()).forEach(System.out::println);
                    menuUserStudent();

                    break;
                case 6:
                    homeMenu();
                    break;
                case 7:
                    System.out.println("God Luck!");
                    System.exit(0);
            }
        } catch (InputMismatchException | ThisLessonHasAlreadyBeenTaken | ThisLessonAlreadyPassed | ThisourseIsNotProvided e) {
            System.out.println(e);
            menuUserStudent();
        }
    }

    public void menuLoginTeacher() {
        try {
            System.out.println("Login  Teacher");
            System.out.print("Enter nationalCode  : ");
            nationalCode = getNumber();
            input.nextLine();
            System.out.print("Enter passWord : ");
            password = input.nextLine();
            if ((professorService.login(Professor.class, nationalCode, password)) != null) {
                professor = professorService.login(Professor.class, nationalCode, password);
                menuUserTeacher();
            } else {
                throw new InvalidUsernameOrpassword();
            }
        } catch (InvalidUsernameOrpassword e) {
            System.out.println(e);
            homeMenu();

        }
    }

    private static Integer calcTerm() {
        Integer currentMonth = now.getMonthValue();
        if (currentMonth <= 4) return 1;
        else if (currentMonth <= 9) return 2;
        else return 3;
    }

    private void menuUserTeacher() {
        try {
            System.out.println("User " + professor.getFirstname() + " " + professor.getLastname());
            System.out.println("||1.Show Information   ||2.input student score   ||3.Show salary   ||4.Previous menu    ||5.Exit");
            int number = getNumber();
            switch (number) {
                case 1:
                    System.out.println(professor);
                    menuUserTeacher();
                    break;
                case 2:
                    System.out.print(" enter student national code:");
                    String nationalCode = input.next();
                    checkNationalCode(nationalCode);
                    System.out.print("course id:");
                    int courseId = getNumber();
                    course = courseService.findById(courseId);
                    System.out.print("score:");
                    Float score = Float.valueOf(getNumber());
                    if (score >= 0 && score <= 20) {
                        Boolean result = allCourseService.findCourseByProfessorId(now.getYear(), calcTerm(), professor.getNationalcode(), course.getName());
                        if (result == true) {

                            GradeCourse gradeCourse = new GradeCourse(now.getYear(), calcTerm(), studentService.findById(Integer.valueOf(nationalCode)), course, score);
                            gradeCourseService.save(gradeCourse);
                            System.out.println("success!");
                            menuUserTeacher();
                        } else throw new ThisLessonIsNotProvided();
                    } else throw new ScoreOutOfRange();
                    break;
                case 3:
                    salary(professor.getStatusEmployee());
                    menuUserTeacher();
                    break;
                case 4:
                    homeMenu();
                    break;
                case 5:
                    System.out.println("God Luck!");
                    System.exit(0);
            }

        } catch (InputMismatchException | ThisLessonIsNotProvided | ScoreOutOfRange e) {
            System.out.println(e);
            menuUserTeacher();
        }
    }

    private static void checkNationalCode(String nationalCode) {
        char[] array = nationalCode.toCharArray();
        for (char a : array) {
            if (!Character.isDigit(a)) throw new InvalidNationalIdException();
        }
        if (nationalCode.length() != 10) throw new InvalidNationalIdException();
    }

    private static void menuManageCourse() {
        try {
            Course course = new Course();
            System.out.println("  Menu Manage Course  ");
            System.out.println("1.add course      2.add course to professor   " + "3.UpDate        4.previous Menu      5.Exit ");
            System.out.println("choose of above Number :");
            int number = getNumber();
            input.nextLine();
            if (number < 6) {
                switch (number) {
                    case 1:
                        System.out.println("Enter name course: ");
                        course.setName(input.nextLine());
                        System.out.println("Enter unit course :");
                        course.setNumberUnit(getNumber());
                        courseService.save(course);
                        System.out.println("saved !!");
                        menuManageCourse();
                        break;
                    case 2:
                        AllCourse allCourse = new AllCourse();
                        System.out.println("Enter course id:");
                        int id = getNumber();
                        if (courseService.findById(id) != null) {
                            allCourse.setCourse(courseService.findById(id));
                        } else {
                            throw new RecordDoesNotExist();
                        }
                        System.out.println("Enter professor id:");
                        id = getNumber();
                        if (professorService.findById(id) != null) {
                            allCourse.setProfessor(professorService.findById(id));
                        } else {
                            throw new RecordDoesNotExist();
                        }
                        allCourse.setTerm(calcTerm());
                        allCourse.setYear(now.getYear());
                        allCourseService.save(allCourse);
                        menuManageCourse();
                        break;
                    case 3:
                        System.out.println("Enter id course :");
                        id = getNumber();
                        course = courseService.findById(id);
                        input.nextLine();
                        System.out.println("Enter name course: ");
                        course.setName(input.nextLine());
                        System.out.println("Enter unit course :");
                        course.setNumberUnit(getNumber());
                        courseService.update(course);
                        System.out.println("updated !!!");
                        menuManageCourse();
                        break;
                    case 4:
                        menuEmployee();
                        break;
                    case 5:
                        System.out.println("God Luck !!!");
                        System.exit(0);
                }
            } else {
                throw new InvalidOption();
            }
        } catch (InvalidOption | RecordDoesNotExist e) {
            System.out.println(e);
            menuManageCourse();
        }
    }
}
