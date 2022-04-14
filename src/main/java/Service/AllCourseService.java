package Service;

import Entity.AllCourse;
import Entity.Course;
import Repository.AllCourseRepository;
import Repository.CourseRepository;
import Util.SessionFactorySingleton;
import org.hibernate.SessionFactory;

import java.util.List;

public class AllCourseService {


   AllCourseRepository allCourseRepository= new AllCourseRepository();

    public AllCourse save(AllCourse allCourse){
        try {
            return allCourseRepository.save(allCourse);

        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    public AllCourse findById(Integer id){
        try {
            return allCourseRepository.findById(AllCourse.class,id);
        }catch (Exception e ){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public void delete(AllCourse allCourse){
        try {
            allCourseRepository.delete(allCourse);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public List<AllCourse> findAll(){
        try {
            return allCourseRepository.findAll(AllCourse.class);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public void update(AllCourse allCourse){
        try {
            allCourseRepository.update(allCourse);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public List<AllCourse> findAllTerm(Integer year, Integer term) {
        try {
            return allCourseRepository.findAllTerm(year, term);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public Boolean findCourseByProfessorId(Integer year, Integer term, Integer prfNationalCode, String courseName) {
        Boolean result = allCourseRepository.findCourseByProfessorId(year, term, prfNationalCode, courseName);
        if (result == false) {
            return false;
        } else
            return true;
    }
}
