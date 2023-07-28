package com.barlo.investment_portfolio.repository;

import com.barlo.investment_portfolio.model.Security;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SecurityRepository extends JpaRepository<Security, Long> {
    List<Security> findByPortfolioId(Long id);
    Optional<Security> findByPortfolioIdAndName(Long id, String name);
    List<Security> findByName(String name);
}
