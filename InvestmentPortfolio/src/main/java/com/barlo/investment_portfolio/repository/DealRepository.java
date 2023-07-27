package com.barlo.investment_portfolio.repository;

import com.barlo.investment_portfolio.model.Deal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DealRepository extends JpaRepository<Deal, Long> {
    Optional<List<Deal>> findAllBySecurityId(Long id);
}
