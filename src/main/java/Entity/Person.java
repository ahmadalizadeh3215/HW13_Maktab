package Entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
public  class Person {
    @Id
    private Integer nationalcode;
    private String firstname;
    private String lastname;
    @Column(length = 10)
    private String password;



    @Override
    public String toString() {
        return " firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", nationalcode='" + nationalcode + '\'' +
                ", password='" + password + '\''
                ;
    }
}
