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
public class AllCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    private  Integer term;
    private Integer year;
    @ManyToOne
    private Course course;
    @ManyToOne
    private Professor professor;

    public AllCourse(Integer term, Integer year, Course course, Professor professor) {
        this.term = term;
        this.year = year;
        this.course = course;
        this.professor = professor;
    }

    @Override
    public String toString() {
        return "AllCourse{" +
                "id=" + id +
                ", term=" + term +
                ", year=" + year +
                ", course name =" + course.getName() +
                ", course unit number =" + course.getNumberUnit() +
                ", employee name =" + professor.getFirstname() +" "+professor.getLastname()+
                '}';
    }
}
