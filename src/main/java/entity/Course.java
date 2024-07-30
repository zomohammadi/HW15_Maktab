package entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = Course.TABLE_NAME)

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Course extends BaseEntity{
    public static final String TABLE_NAME = "course";
    public static final String CAPACITY = "capacity";
  //  public static final String TEACHER_ID = "teacher_id";

    @Column(name = CAPACITY)
    private Integer capacity;

    @OneToOne
  //  @JoinColumn(name = TEACHER_ID)
    private Teacher teacher;

    @OneToOne
    private Term term;

    @OneToOne
    private Lesson lesson;

}
