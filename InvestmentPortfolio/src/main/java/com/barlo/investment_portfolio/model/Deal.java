package com.barlo.investment_portfolio.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Deal extends AbstractBaseEntity {

    private LocalDateTime dateTime;
    private float price;
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Security.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "security_id", nullable = false)
    @JsonManagedReference
    private Security security;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deal deal = (Deal) o;
        return id.equals(deal.getId())
                && dateTime.equals(deal.dateTime)
                && price == deal.getPrice()
                && quantity == deal.getQuantity()
                && security.getId().equals(deal.getSecurity().getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateTime, price, quantity, security);
    }

    @Override
    public String toString() {
        return "Deal{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", price=" + price +
                ", quantity=" + quantity +
                ", security id=" + security.getId() +
                '}';
    }
}
