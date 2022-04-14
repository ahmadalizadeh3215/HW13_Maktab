package Entity;

import lombok.*;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer numberUnit;
    public Course(String name, Integer numberUnit) {
        this.name = name;
        this.numberUnit = numberUnit;
    }

}
