package entity;


import enumration.TeacherType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = Teacher.TABLE_NAME, indexes = {
        @Index(name = "unique_index_"+Teacher.TABLE_NAME,columnList = User.USERNAME+","+ User.PASSWORD,unique = true)
})
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Teacher extends User{

    public static final String TABLE_NAME = "teacher";
    public static final String TEACHER_CODE="teacher_code";
    public static final String TEACHER_TYPE="teacher_type";
    public static final String BASE_SALARY="base_salary";


    @Column(name = TEACHER_CODE,unique = true)
    private String teacherCode;

    @Column(name = TEACHER_TYPE)
    @Enumerated(EnumType.STRING)
    private TeacherType teacherType;

    @Column(name = BASE_SALARY)
    private Double baseSalary;

    @Override
    public String toString() {
        return "Teacher{" +
               " FirstName= " + super.getFirstName()
               + ", LastName= " + super.getLastName()
               + ", MobileNumber= " + super.getMobileNumber()
               + ", NationalCode= " + super.getNationalCode()
               + ", teacherCode='" + teacherCode +
               ", teacherType=" + teacherType +
               ", baseSalary=" + baseSalary +
               '}';
    }
}
