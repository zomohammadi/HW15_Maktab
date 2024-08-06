package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = CountUnit.TABLE_NAME,
        indexes = {
                @Index(name = "unique_index_" + CountUnit.TABLE_NAME, columnList = CountUnit.STUDENT_ID + "," + CountUnit.TERM_ID, unique = true)
        })

@SuperBuilder

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class CountUnit extends BaseEntity {
    public static final String TABLE_NAME = "countunit";
    public static final String STUDENT_ID = "student_id";
    public static final String TERM_ID = "term_id";
    public static final String Count_Of_Selected_Unit = "count_of_selected_unit";

    @ManyToOne
    @JoinColumn(name = STUDENT_ID)
    private Student student;

    @ManyToOne
    @JoinColumn(name = TERM_ID)
    private Term term;

    @Column(name = Count_Of_Selected_Unit)
    private Integer countOfSelectedUnit;

}
