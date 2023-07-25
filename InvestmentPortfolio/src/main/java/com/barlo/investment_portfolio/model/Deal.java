package com.barlo.investment_portfolio.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Deal {

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
    private LocalDateTime dateTime;
    private Integer price;
    private Long quantity;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Security.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "security_id", nullable = false)
    @JsonManagedReference
    private Security security;
}
