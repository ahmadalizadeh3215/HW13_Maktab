package Service;

import Entity.Student;
import Repository.StudentRepository;

import java.util.List;

public class StudentService {
    StudentRepository studentRepository= new StudentRepository();
    public Student save(Student student){
        try {
            return studentRepository.save(student);

        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    public Student findById(Integer id){
        try {
            return studentRepository.findById(Student.class,id);
        }catch (Exception e ){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public void delete(Student student){
        try {
            studentRepository.delete(student);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public List<Student> findAll(){
        try {
            return studentRepository.findAll(Student.class);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public void update(Student student){
        try {
            studentRepository.update(student);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public Student login(Class<Student> studentClass, Integer nationalCode, String password){
        try {
            return studentRepository.login(Student.class,nationalCode,password);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public Integer deleteById(Class<Student> studentClass, Integer nationalCode){
        try {
            return studentRepository.deleteById(Student.class,nationalCode);
        }catch (Exception e ){
            System.out.println(e.getMessage());
        }
        return -1;
    }

}
