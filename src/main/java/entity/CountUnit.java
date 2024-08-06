package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = CountUnit.TABLE_NAME)

@SuperBuilder

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class CountUnit extends BaseEntity {
    public static final String TABLE_NAME = "countunit";

    @OneToOne
    private Student student;

    @OneToOne
    private Term term;

    private Integer count;

}
