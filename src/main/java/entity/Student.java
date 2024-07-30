package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = Student.TABLE_NAME)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Student extends User implements Serializable {

    public static final String TABLE_NAME = "student";

    public static final String STUDENT_CODE = "student_code";
    public static final String ENTERING_YEAR = "entering_year";


    @Column(name = STUDENT_CODE)
    private String studentCode;


    @Column(name = ENTERING_YEAR)
    private Integer enteringYear;



}
