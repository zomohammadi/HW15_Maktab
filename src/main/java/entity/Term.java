package entity;

import enumration.TermValue;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = Term.TABLE_NAME)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Term extends BaseEntity{

    public static final String TABLE_NAME = "term";
    public static final String TERM_VALUE = "term_value";
    public static final String YEAR = "year";

    @Column(name = TERM_VALUE)
    @Enumerated(EnumType.STRING)
    private TermValue termValue;

    @Column(name = YEAR)
    public Integer year;


    @Override
    public String toString() {
        return "Term{" +
               "termValue=" + termValue +
               ", year=" + year +
               '}';
    }
}
