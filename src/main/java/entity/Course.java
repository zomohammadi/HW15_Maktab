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
public class Course extends BaseEntity {
    public static final String TABLE_NAME = "course";
    public static final String CAPACITY = "capacity";
    //  public static final String TEACHER_ID = "teacher_id";

    @Column(name = CAPACITY)
    private Integer capacity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    //  @JoinColumn(name = TEACHER_ID)
    private Teacher teacher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Term term;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Lesson lesson;

    @Override
    public String toString() {
        return "Course{" +
               "course id = " + super.getId() +
               ", capacity=" + capacity +
               ", teacher FirstName= " + teacher.getFirstName() +
               ", teacher LastName=" + teacher.getLastName()+
               ", term year=" + term.getYear() +
               ", term =" + term.getTermValue()  +
               ", lesson=" + lesson +
               '}';
    }
}
