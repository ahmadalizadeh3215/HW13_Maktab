package Entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Employee extends Person {
    private Integer salary;
    @Enumerated(EnumType.STRING)
    private StatusEmployee statusEmployee;

    public Employee(Integer nationalcode, String firstname, String lastname
            , String password, Integer salary, StatusEmployee statusEmployee) {
        super(nationalcode, firstname, lastname,  password);
        this.salary = salary;
        this.statusEmployee = statusEmployee;
    }

    @Override
    public String toString() {
        return "{" +
                "salary=" + salary +
                ", status=" + statusEmployee +
                "} " + super.toString();
    }
}
