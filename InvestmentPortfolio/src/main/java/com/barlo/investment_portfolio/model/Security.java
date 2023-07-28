package com.barlo.investment_portfolio.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import lombok.experimental.SuperBuilder;


import javax.persistence.*;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Security extends AbstractBaseEntity {

    private String name;

    private float averagePrice;

    private int quantity;

    @Enumerated(EnumType.STRING)
    private Type type;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "security", orphanRemoval = true)
    @JsonBackReference
    private List<Deal> dealsHistory;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Portfolio.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "portfolio_id")
    @JsonManagedReference
    private Portfolio portfolio;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Security security = (Security) o;
        List<Deal> proposedDeals = security.getDealsHistory();

        if (dealsHistory.size() != proposedDeals.size()) return false;

        Iterator<Deal> expected = dealsHistory.iterator();
        Iterator<Deal> proposed = proposedDeals.iterator();

        while (expected.hasNext() && proposed.hasNext()) {
            if (!Objects.equals(expected.next().getId(), proposed.next().getId())) return false;
        }


        return id.equals(security.getId())
                && name.equals(security.name)
                && averagePrice == security.getAveragePrice()
                && quantity == security.getQuantity()
                && type.equals(security.getType())
                && portfolio.getId().equals(security.getPortfolio().getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, averagePrice, quantity, type, dealsHistory, portfolio);
    }

    @Override
    public String toString() {
        return "Security{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", averagePrice=" + averagePrice +
                ", quantity=" + quantity +
                ", type=" + type +
                ", portfolio=" + portfolio.getId() +
                ", dealsHistory= " + dealsHistory +
                '}';
    }
}
