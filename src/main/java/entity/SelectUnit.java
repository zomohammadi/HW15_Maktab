package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = SelectUnit.TABLE_NAME)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SelectUnit extends BaseEntity {

public static final String TABLE_NAME ="SelectUnit" ;

    @OneToOne
    private Student student;
   // private Set<Student> student;

    @OneToOne
    private Course course;
    //private Set<Course> course;

    private Integer score;


}
