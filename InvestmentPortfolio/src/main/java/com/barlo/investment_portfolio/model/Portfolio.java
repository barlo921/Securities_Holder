package com.barlo.investment_portfolio.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Portfolio extends AbstractBaseEntity {

    private Long userId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "portfolio")
    @JsonBackReference
    private List<Security> securities;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Portfolio portfolio = (Portfolio) o;
        List<Security> proposedSecurities = portfolio.getSecurities();

        if (securities.size() != proposedSecurities.size()) return false;

        Iterator<Security> expected = securities.iterator();
        Iterator<Security> proposed = proposedSecurities.iterator();

        while (expected.hasNext() && proposed.hasNext()) {
            if (!Objects.equals(expected.next().getId(), proposed.next().getId())) return false;
        }

        return id.equals(portfolio.getId())
                && userId.equals(portfolio.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, securities);
    }
}
