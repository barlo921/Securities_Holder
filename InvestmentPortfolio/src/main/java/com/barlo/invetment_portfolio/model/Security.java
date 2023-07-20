package com.barlo.invetment_portfolio.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Security {
    private Integer id;
    private String name;
    private Double averagePrice;
    private Integer quantity;
    private Type type;
    private List<Deal> dealsHistory;
}
