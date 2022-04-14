package Service;


import Entity.Course;
import Entity.Student;
import ExceptionManagment.RecordDoesNotExist;
import Repository.CourseRepository;
import Util.SessionFactorySingleton;
import org.hibernate.SessionFactory;

import java.sql.SQLException;
import java.util.List;

public class CourseService {

    CourseRepository courseRepository= new CourseRepository();
  public Course save(Course course){
    try {
     return courseRepository.save(course);

    }catch (Exception e){
       System.out.println(e.getMessage());
       return null;
    }
  }
public Course findById(Integer id){
      try {
          return courseRepository.findById(Course.class,id);
      }catch (Exception e ){
          System.out.println(e.getMessage());
      }
      return null;
}
public void delete(Course course){
      try {
          courseRepository.delete(course);
      }catch (Exception e){
          System.out.println(e.getMessage());
      }
}

    public List<Course> findAll(){
        try {
            return courseRepository.findAll(Course.class);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public void update(Course course){
      try {
          courseRepository.update(course);
      }catch (Exception e){
          System.out.println(e.getMessage());
      }
    }
    public void deleteById(Class<Course>courseClass,Integer id){
        try {
            courseRepository.deleteById(Course.class,id);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }


    public Course findByName(String name) {
        try {
            return courseRepository.findByName(name);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
