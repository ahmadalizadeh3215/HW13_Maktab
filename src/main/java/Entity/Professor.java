package Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Professor extends Person{
    private Integer salary;
    @Enumerated(EnumType.STRING)
    private StatusEmployee statusEmployee;


    public Professor() {
    }

    public Professor(Integer nationalcode, String firstname, String lastname, String password, Integer salary, StatusEmployee statusEmployee) {
        super(nationalcode, firstname, lastname, password);
        this.salary = salary;
        this.statusEmployee = statusEmployee;
    }


    @Override
    public String toString() {
        return "(Professor: " +
                super.toString()+
                " salary='"+salary+
                "', statusProfessor='"+statusEmployee+
                "')";
    }
}
