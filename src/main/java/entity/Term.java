package entity;

import enumration.TermValue;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
    public static final String IS_ACTIVE = "is_active";

    @Column(name = TERM_VALUE)
    private TermValue termValue;

    @Column(name = YEAR)
    public Integer year;

    @Column(name = IS_ACTIVE)
    private boolean isActive;


}
