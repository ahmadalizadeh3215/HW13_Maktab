package Service;

import Entity.Professor;

import Entity.StatusEmployee;
import Repository.ProfessorRepository;


import java.util.List;

public class ProfessorService {
    ProfessorRepository professorRepository=new ProfessorRepository();
    public Professor save(Professor professor){
        try {
            return professorRepository.save(professor);

        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    public Professor findById(Integer id){
        try {
            return professorRepository.findById(Professor.class,id);
        }catch (Exception e ){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public void delete(Professor professor){
        try {
            professorRepository.delete(professor);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public List<Professor> findAll(){
        try {
            return professorRepository.findAll(Professor.class);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public void update(Professor professor){
        try {
            professorRepository.update(professor);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    public Professor login(Class<Professor> professorClass,Integer nationalcode, String password){
        try {
            return professorRepository.login(Professor.class,nationalcode,password);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public Integer findCourseUnitById(Class<Professor>professorClass, Integer nationalcode,Integer year,Integer term) {
        try {
            return professorRepository.findCourseUnitById(nationalcode, year, term);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public Integer calcSalary(Professor professor,Integer totalOfUnit,Integer year ,Integer term){
        if (professor.getStatusEmployee().equals(StatusEmployee.tuition)){
            return totalOfUnit*1000000;
        }else if (professor.getStatusEmployee().equals(StatusEmployee.TrainingEmployee)){
            return professor.getSalary();

        }else return totalOfUnit *1000000+ professor.getSalary();
    }
    }

