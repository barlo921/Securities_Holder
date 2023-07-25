package com.barlo.investment_portfolio.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;


import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Security {

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

    private String name;

    private Integer averagePrice;

    private Long quantity;

    @Enumerated(EnumType.STRING)
    private Type type;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "security")
    @JsonBackReference
    private List<Deal> dealsHistory;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Portfolio.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "portfolio_id")
    @JsonManagedReference
    private Portfolio portfolio;

}
