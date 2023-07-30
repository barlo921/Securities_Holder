package com.barlo.investment_portfolio.repository;

import com.barlo.investment_portfolio.model.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
    List<Portfolio> findByUserId(Long id);
    Optional<Portfolio> findByIdAndUserId(Long id, Long userId);
    void deleteByUserId(Long userId);
}
