package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = SelectUnit.TABLE_NAME)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

@SuperBuilder
public class SelectUnit extends BaseEntity {

public static final String TABLE_NAME ="SelectUnit" ;

    @ManyToOne
    @JoinColumn
    private Student student;
   // private Set<Student> student;

    @ManyToOne
    @JoinColumn
    private Course course;
    //private Set<Course> course;

    private Double score;


}
