package com.barlo.investment_portfolio.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Portfolio {

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
    private Long id;

    private Long userId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "portfolio")
    @JsonBackReference
    private List<Security> securities;

}
