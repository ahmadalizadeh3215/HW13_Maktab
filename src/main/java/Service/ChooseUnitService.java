

package Service;


import Entity.ChooseUnit;
import ExceptionManagment.*;
import Repository.ChooseUnitRepository;


import java.time.LocalDateTime;
import java.util.List;

public class ChooseUnitService {
    private final GradeCourseService gradeCourseService= new GradeCourseService();
    private final  ChooseUnitRepository chooseUnitRepository =new ChooseUnitRepository();
    private int currentYear;
    private int currentTerm;
    private int pastYear;
    private int pastTerm;

    public void save(ChooseUnit chooseUnit) {
        Boolean result = gradeCourseService.checkLessonPassed(chooseUnit.getStudent().getNationalcode(),
                chooseUnit.getAllCourse().getCourse().getName());
        if (result == true)
            throw new ThisLessonAlreadyPassed();
        Boolean result1 = checkCourseGiven(chooseUnit.getStudent().getNationalcode(),
                chooseUnit.getAllCourse().getCourse().getName(),chooseUnit.getYear(),chooseUnit.getTerm());
        if (result1 == true)
            throw new ThisLessonHasAlreadyBeenTaken();
        currentYear = chooseUnit.getYear();
        currentTerm = chooseUnit.getTerm();
        calcPastTerm();
        Double gradeAverage =gradeCourseService.calculateAvarage(chooseUnit.getStudent().getNationalcode(), pastYear, pastTerm);
        Integer countOfUnit;
        if (gradeAverage >= 18.0) {
            countOfUnit = 24;
        } else
            countOfUnit = 20;
        Integer tedad = calcTotalUnit(chooseUnit.getStudent().getNationalcode(), chooseUnit.getYear(), chooseUnit.getTerm());
        if (tedad <= countOfUnit) {
            chooseUnitRepository.save(chooseUnit);
        } else
            throw new UnitSelectionCeiling();
    }

    public List<ChooseUnit> findAll(){
        try {
            return chooseUnitRepository.findAll(ChooseUnit.class);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public void update(ChooseUnit chooseUnit){
        try {
            chooseUnitRepository.update(chooseUnit);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void delete(ChooseUnit chooseUnit){
        try {
            chooseUnitRepository.delete(chooseUnit);
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public Integer calcTotalUnit(Integer nationalCode, Integer year, Integer term) {
        Integer result = chooseUnitRepository.calcTotalUnit(nationalCode, year, term);
        if (result == null)
            return 0;
        else
            return result;
    }

    public Boolean checkCourseGiven(Integer nationalCode, String courseName, Integer year, Integer term) {
        return chooseUnitRepository.checkCourseGiven(nationalCode, courseName, year, term);
    }
    private void calcPastTerm() {
        if (currentTerm == 1) {
            pastYear = currentYear - 1;
            pastTerm = 3;
        } else {
            pastTerm = -1;
        }
    }
    public List<ChooseUnit>findByNationalCodeStuCourse(Integer nationalCode){
        try {
            return chooseUnitRepository.findByNationalCodeStuCourse(nationalCode);
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

}
