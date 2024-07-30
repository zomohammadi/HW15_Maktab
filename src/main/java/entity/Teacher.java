package entity;


import enumration.TeacherType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = Teacher.TABLE_NAME)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Teacher extends User{

    public static final String TABLE_NAME = "teacher";
    public static final String TEACHER_CODE="teacher_code";
    public static final String TEACHER_TYPE="teacher_type";
    public static final String BASE_SALARY="base_salary";


    @Column(name = TEACHER_CODE)
    private String teacherCode;

    @Column(name = TEACHER_TYPE)
    private TeacherType teacherType;

    @Column(name = BASE_SALARY)
    private Double baseSalary;


}
