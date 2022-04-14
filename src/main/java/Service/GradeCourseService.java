package Service;

import Entity.Employee;
import Entity.GradeCourse;
import Repository.EmployeeRepository;
import Repository.GradeCourseRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public class GradeCourseService {
    GradeCourseRepository gradeCourseRepository= new GradeCourseRepository();

    public GradeCourse save(GradeCourse gradeCourse) {
        try {
            return gradeCourseRepository.save(gradeCourse);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public GradeCourse findById(Integer id) {
        try {
            return gradeCourseRepository.findById(GradeCourse.class, id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void delete(GradeCourse gradeCourse) {
        try {
            gradeCourseRepository.delete(gradeCourse);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<GradeCourse> findAll() {
        try {
            return gradeCourseRepository.findAll(GradeCourse.class);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void update(GradeCourse gradeCourse) {
        try {
            gradeCourseRepository.update(gradeCourse);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public Double calculateAvarage(Integer nationalCode, Integer year, Integer term){
        Double result =  GradeCourseRepository.calculateAvarage(nationalCode,year,term);
        if (result == null)
            return 17.0;
        else
            return result;
    }

    public List<GradeCourse> passedCourse(Integer nationalCode) {
        try {
            return gradeCourseRepository.passedCourse(nationalCode);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Boolean checkLessonPassed(Integer nationalCode, String coursename) {

        try {
            return gradeCourseRepository.checkLessonPassed(nationalCode, coursename);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
       return false;
    }



}
