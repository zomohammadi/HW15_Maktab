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
@Table(name = Employees.TABLE_NAME)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Employees extends User implements Serializable {

    public static final String TABLE_NAME = "employee";

    public static final String PERSONNEL_CODE="personnel_code";
    public static final String SALARY="salary";

    @Column(name = PERSONNEL_CODE)
    private String PersonnelCode;

    @Column(name = SALARY)
    private Double salary;
}
