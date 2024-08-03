package entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = Course.TABLE_NAME)
@SuperBuilder
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

    @ManyToOne
    @JoinColumn
  //  @JoinColumn(name = TEACHER_ID)
    private Teacher teacher;

    @ManyToOne
    @JoinColumn
    private Term term;

    @ManyToOne
    @JoinColumn
    private Lesson lesson;

}
