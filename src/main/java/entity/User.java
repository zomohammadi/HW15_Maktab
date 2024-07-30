package entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class User extends BaseEntity implements Serializable {

    public static final String FIRST_NAME = "first_Name";
    public static final String LAST_NAME = "last_Name";
    public static final String NATIONAL_CODE = "national_Code";
    public static final String MOBILE_NUMBER = "mobile_Number";
  //  public static final String USER_TYPE = "user_type";


    @Column(name = FIRST_NAME)
    private String firstName;

    @Column(name = LAST_NAME)
    private String lastName;

    @Column(name = NATIONAL_CODE)
    private String nationalCode;

    @Column(name = MOBILE_NUMBER)
    private String mobileNumber;

//    @Enumerated(value = EnumType.STRING)
//    @Column(name = USER_TYPE)
//    private UserType userType;


}
