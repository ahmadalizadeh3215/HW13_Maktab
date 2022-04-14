package Entity;

import lombok.*;

import javax.persistence.*;
@Getter
@Setter
@Entity
public class Student extends Person {

    public Student(Integer nationalcode, String firstname, String lastname, String password) {
        super(nationalcode, firstname, lastname, password);
    }

    public Student() {
    }

    @Override
    public String toString() {
        return "Student : " + super.toString();
    }
}
