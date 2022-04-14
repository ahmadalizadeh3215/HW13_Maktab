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
public class GradeCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer year;
    private Integer term;
    @ManyToOne
    private Student student;
    @ManyToOne
    private Course course;
    private float grade;

    public GradeCourse(Integer year, Integer term, Student student, Course course, float grade) {
        this.year = year;
        this.term = term;
        this.student = student;
        this.course = course;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "LessonScores{" +
                "id=" + id +
                ", year=" + year +
                ", term=" + term +
                ", student=" + student.getNationalcode() +
                ", course=" + course.getName() +
                ", grade=" + grade +
                '}';
    }

}
