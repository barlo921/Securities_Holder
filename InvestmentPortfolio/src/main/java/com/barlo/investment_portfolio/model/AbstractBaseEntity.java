package com.barlo.investment_portfolio.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@MappedSuperclass
@Access(AccessType.FIELD)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public abstract class AbstractBaseEntity {

    @Id
    @SequenceGenerator(
            name = "securities_id_sequence",
            sequenceName = "securities_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "securities_id_sequence"
    )
    protected Long id;

    protected boolean isNew() {
        return this.id == null;
    }

}
