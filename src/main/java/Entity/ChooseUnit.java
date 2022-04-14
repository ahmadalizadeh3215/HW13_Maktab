package Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class ChooseUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    private Integer year;
    private Integer term;
    @ManyToOne
    private  Student student;
    @ManyToOne
    private AllCourse allCourse;


    public ChooseUnit(Integer year, Integer term, Student student, AllCourse allCourse) {
        this.year = year;
        this.term = term;
        this.student = student;
        this.allCourse = allCourse;
    }

    @Override
    public String toString() {
        return "ChooseUnit {" +
                "term=" + term +
                ", student=" + student.getNationalcode() +
                ", allCourse=" + allCourse.getCourse().getName() +
                '}';
    }
}
