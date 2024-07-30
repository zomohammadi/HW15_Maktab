package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = Employee.TABLE_NAME ,uniqueConstraints = {
        @UniqueConstraint(name = "Unique_Constraint_" + Employee.TABLE_NAME, columnNames = {User.USERNAME, User.PASSWORD})
})
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Employee extends User {

    public static final String TABLE_NAME = "employee";

    public static final String PERSONNEL_CODE = "personnel_code";
    public static final String SALARY = "salary";

    @Column(name = PERSONNEL_CODE, unique = true)
    private String PersonnelCode;

    @Column(name = SALARY)
    private Double salary;

    @Override
    public String toString() {
        return "Employee{" +
                " FirstName= " + super.getFirstName()
                + ", LastName= " + super.getLastName()
                + ", MobileNumber= " + super.getMobileNumber()
                + ", NationalCode= " + super.getNationalCode()
                + ", PersonnelCode='" + PersonnelCode
                + ", salary=" + salary +
                "}";
    }
}