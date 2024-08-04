package entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = Lesson.TABLE_NAME)
@SuperBuilder

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Lesson extends BaseEntity {
    public static final String TABLE_NAME = "Lesson";
    public static final String TITLE = "title";
    public static final String UNIT = "unit";

    @Column(name = TITLE)
    private String title;

    @Column(name = UNIT)
    private Integer unit;

    @Override
    public String toString() {
        return "Lesson{" +
               "title='" + title +
               ", unit=" + unit +
               '}';
    }
}
