package com.barlo.invetment_portfolio.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Deal {
    private Integer id;
    private LocalDateTime dateTime;
    private Double price;
    private Integer quantity;
}
