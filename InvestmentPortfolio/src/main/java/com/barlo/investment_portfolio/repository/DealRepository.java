package com.barlo.investment_portfolio.repository;

import com.barlo.investment_portfolio.model.Deal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealRepository extends JpaRepository<Deal, Long> {
}
