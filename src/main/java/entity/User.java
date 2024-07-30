package entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass()

public class User extends BaseEntity{

    public static final String FIRST_NAME = "first_Name";
    public static final String LAST_NAME = "last_Name";
    public static final String NATIONAL_CODE = "national_Code";
    public static final String MOBILE_NUMBER = "mobile_Number";
    public static final String USERNAME="username";
    public static final String PASSWORD="password";

    @Column(name = FIRST_NAME)
    private String firstName;

    @Column(name = LAST_NAME)
    private String lastName;

    @Column(name = NATIONAL_CODE)
    private String nationalCode;

    @Column(name = MOBILE_NUMBER)
    private String mobileNumber;

    @Column(name = USERNAME,nullable = false)
    private String username;

    @Column(name = PASSWORD,nullable = false)
    private String password;

}
